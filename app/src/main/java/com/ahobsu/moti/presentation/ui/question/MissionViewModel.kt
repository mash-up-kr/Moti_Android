package com.ahobsu.moti.presentation.ui.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.AnswerUseCase
import com.ahobsu.moti.domain.MissionUseCase
import com.ahobsu.moti.domain.entity.Answer
import com.ahobsu.moti.domain.entity.Mission
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.domain.repository.MissionRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.question.model.MissionItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class MissionViewModel(
    private val missionRepository: MissionRepository,
    private val answerRepository: AnswerRepository
) : BaseViewModel() {

    private val _missionList = MutableLiveData<List<MissionItemModel>>()
    val missionList: LiveData<List<MissionItemModel>> = _missionList

    private val _selectMission = MutableLiveData<MissionItemModel>()
    val selectMission: LiveData<MissionItemModel> = _selectMission


    private val _missionAnswer = MutableLiveData<AnswerModel>()
    val missionAnswer: LiveData<AnswerModel> = _missionAnswer

    val answerContent = MutableLiveData<String>()

    val complete = MutableLiveData<Unit>()
    val getImage = MutableLiveData<Unit>()

    fun initMission() {
        getMissions()
    }

    fun onClickAnswerImage() {
        getImage.postValue(Unit)
    }

    fun onClickComplete() {
        Log.e("123","onClickComplete")
        postAnswer()
    }
    fun onClickBack() {
        //TODO:: back
    }
    fun onClickReset() {
        missionsRequest(MissionUseCase(missionRepository).getRefreshMissions())
    }

    fun setAnswerImage(img:String){
        selectMission.value?.id?.let {
            val base =AnswerModel(
                content = _missionAnswer.value?.content,
                missionId = it,
                file = img
            )
            _missionAnswer.postValue(base)
        }
    }
    fun setAnswerContent(){
        Log.e("answerContent ",answerContent.value?:"")
        answerContent.value?:return
        selectMission.value?.id?.let {
            val base =AnswerModel(
                answerContent.value!!,
                it,
                _missionAnswer.value?.file
            )
            _missionAnswer.postValue(base)
        }
    }

    fun getMission(missionId: Int) {
        MissionUseCase(missionRepository).getMissionId(missionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("getMissions ", it.toString())
                _selectMission.postValue(
                    MissionItemModel(
                        id = it.id ?: 0,
                        title = it.title ?: "",
                        isContent = it.isContent ?: true,
                        isImage = it.isImage ?: true
                    )
                )
            }, { e ->
                Log.e("e", e.toString())
            })
    }

    fun postAnswer() {
        selectMission.value?.id?.let {
            AnswerUseCase(answerRepository).postAnswer(
                Answer(
                    content = missionAnswer.value?.content,
                    file = missionAnswer.value?.file,
                    missionId = it
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    Log.e("postAnswer ", it.toString())
                    if (it)
                        complete.postValue(Unit)

                }, { e ->
                    Log.e("e", e.toString())
                })
        }
    }

    private fun getMissions() {
        missionsRequest((MissionUseCase(missionRepository).getMissions()))
    }

    private fun missionsRequest(req: Single<List<Mission>>) {
        req.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("getMissions ", it.toString())
                _missionList.postValue(mapMissionItem(it))
            }, { e ->
                Log.e("e", e.toString())
            })
    }

    private fun mapMissionItem(items: List<Mission>): List<MissionItemModel> {
        return items.map {
            MissionItemModel(
                id = it.id ?: 0,
                title = it.title ?: "",
                isContent = it.isContent ?: true,
                isImage = it.isImage ?: true
            )
        }
    }
}