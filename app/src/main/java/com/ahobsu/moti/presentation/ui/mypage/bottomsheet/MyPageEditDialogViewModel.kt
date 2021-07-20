package com.ahobsu.moti.presentation.ui.mypage.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.util.EnumClass


class MyPageEditDialogViewModel : BaseViewModel() {

    private val _userGender = MutableLiveData<EnumClass.Gender>()
    val userGender: LiveData<EnumClass.Gender> = _userGender

    fun onSelectGender(gender: EnumClass.Gender) {
        _userGender.value = gender
    }

}