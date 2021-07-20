package com.ahobsu.moti.presentation.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.domain.repository.UserRepository
import com.ahobsu.moti.presentation.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class MyPageViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val profileUrl: LiveData<String> =
        MutableLiveData<String>("https://avatars.githubusercontent.com/u/18034145?v=4")

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    val userNickName = MutableLiveData<String>()

    private val _selectedBirthday = MutableLiveData<Unit>()
    val selectedBirthday: LiveData<Unit> = _selectedBirthday

    private val _selectedGender = MutableLiveData<Unit>()
    val selectedGender: LiveData<Unit> = _selectedGender

    private val _btnSaveMyPage = MutableLiveData<Unit>()
    val btnSaveMyPage: LiveData<Unit> = _btnSaveMyPage


    init {
        userRepository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _user.postValue(it)
                userNickName.postValue(it.name)
                Log.e(" Success ", it.toString())
            }, { e ->
                Log.e("postSignIn e", e.toString())
            })
    }

    fun setUserGender(string: String) {
        user.value?.let {
            _user.value = User(
                id = it.id,
                birthday = it.birthday,
                email = it.email,
                name = it.name,
                gender = string,
                refreshDate = it.refreshDate,
                refreshToken = it.refreshToken,
                mission = it.mission,
                snsId = it.snsId,
                snsType = it.snsType,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    fun setUserBirthday(string: String) {
        //TODO:: 마이페이지에서 쓰는 User data class 간단하게 변경
        user.value?.let {
            _user.value = User(
                id = it.id,
                birthday = string,
                email = it.email,
                name = it.name,
                gender = it.gender,
                refreshDate = it.refreshDate,
                refreshToken = it.refreshToken,
                mission = it.mission,
                snsId = it.snsId,
                snsType = it.snsType,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    fun onSelectedGender() {
        _selectedGender.value = Unit
    }

    fun onSelectedBirthday() {
        _selectedBirthday.value = Unit
    }

    fun onClickSavedMyPage() {
        user.value?.let {
            userRepository.putUserInfo(user = it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(" Success ", it.toString())
                    _btnSaveMyPage.postValue(Unit)
                }, { e ->
                    Log.e("postSignIn e", e.toString())
                })
        }
    }
}