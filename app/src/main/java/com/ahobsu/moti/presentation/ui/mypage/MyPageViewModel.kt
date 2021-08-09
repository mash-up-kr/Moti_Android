package com.ahobsu.moti.presentation.ui.mypage

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.UserUseCase
import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.domain.repository.UserRepository
import com.ahobsu.moti.presentation.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class MyPageViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _profileUrl = MutableLiveData<String>()
    val profileUrl: LiveData<String> = _profileUrl

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    val userNickName = MutableLiveData<String>("닉네임을입력해주세요")

    private val _selectedBirthday = MutableLiveData<Unit>()
    val selectedBirthday: LiveData<Unit> = _selectedBirthday

    private val _selectedGender = MutableLiveData<Unit>()
    val selectedGender: LiveData<Unit> = _selectedGender

    private val _btnSaveMyPage = MutableLiveData<Unit>()
    val btnSaveMyPage: LiveData<Unit> = _btnSaveMyPage


    init {
        UserUseCase(userRepository).getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _user.postValue(it)
                _profileUrl.postValue(it.profileUrl ?: "")
                userNickName.postValue(it.name ?: "닉네임을입력해주세요")
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
                profileUrl = it.profileUrl,
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
                profileUrl = it.profileUrl,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    fun setUserProfile(uri: Uri) {
        UserUseCase(userRepository).putUserProfile(uri)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _profileUrl.postValue(uri.toString())
                Log.e(" Success ", it.toString())
            }, { e ->
                Log.e("postSignIn e", e.toString())
            })
    }


    fun onSelectedGender() {
        _selectedGender.value = Unit
    }

    fun onSelectedBirthday() {
        _selectedBirthday.value = Unit
    }

    fun onClickUserAgreement(context: Context) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.notion.so/ahobsu/MOTI-35d01dd331bb4aa0915c33d28d60b63c")
            )
        )
    }

    fun onClickSavedMyPage() {
        user.value?.let {
            userRepository.putUserInfo(
                user = User(
                    id = it.id,
                    birthday = it.birthday,
                    email = it.email,
                    name = userNickName.value,
                    gender = it.gender,
                    refreshDate = it.refreshDate,
                    refreshToken = it.refreshToken,
                    mission = it.mission,
                    snsId = it.snsId,
                    snsType = it.snsType,
                    profileUrl = it.profileUrl,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt
                )
            )
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