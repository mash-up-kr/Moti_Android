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
    val user:LiveData<User> = _user


    init {
        userRepository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _user.postValue(it)
                Log.e(" Success ", it.toString())
            }, { e ->
                Log.e("postSignIn e", e.toString())
            })
    }

    fun onClickSavedMyPage(){

    }
}