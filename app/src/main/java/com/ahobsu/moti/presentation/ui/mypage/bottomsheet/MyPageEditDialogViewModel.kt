package com.ahobsu.moti.presentation.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.repository.UserRepository
import com.ahobsu.moti.presentation.BaseViewModel
import com.ahobsu.moti.presentation.ui.util.EnumClass


class MyPageEditDialogViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val userNickName = MutableLiveData<String>()

    private val _isGender = MutableLiveData<Boolean>()
    val isGender: LiveData<Boolean> = _isGender

    private val _userGender = MutableLiveData<EnumClass.Gender>()
    val userGender: LiveData<EnumClass.Gender> = _userGender

    private val _toTheNextStep = MutableLiveData<Boolean>(false)
    val toTheNextStep: LiveData<Boolean> = _toTheNextStep

    private val _selectedBirthday = MutableLiveData<Unit>()
    val selectedBirthday: LiveData<Unit> = _selectedBirthday

    private val _selectedGender = MutableLiveData<Unit>()
    val selectedGender: LiveData<Unit> = _selectedGender


    init {
    }

    fun init(boolean: Boolean) {
        _isGender.postValue(boolean)
    }

    fun onSelectedGender() {
        _selectedGender.value = Unit
    }

    fun onSelectedBirthday() {
        _selectedBirthday.value = Unit
    }

    fun onSelectGender(gender: EnumClass.Gender) {
        _toTheNextStep.value = true
        _userGender.value = gender
    }

    fun onClickSavedMyPage() {

    }


}