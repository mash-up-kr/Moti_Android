package com.ahobsu.moti.presentation.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.presentation.BaseViewModel

class LoginViewModel : BaseViewModel() {

    enum class SignUpFragment { Birthday, Complete, Gender, NickName, EXIT }
    enum class Gender { MAN, WOMAN }

    private val _signUpFragment: MutableLiveData<SignUpFragment> = MutableLiveData()
    val signUpFragment: LiveData<SignUpFragment> = _signUpFragment

    private val _popFragment = MutableLiveData<Unit>()
    val popFragment: LiveData<Unit> = _popFragment

    val etNickname = MutableLiveData<String>()

    private val _userGender = MutableLiveData<Gender>()
    val userGender: LiveData<Gender> = _userGender

    private val _toTheNextStep = MutableLiveData<Boolean>(false)
    val toTheNextStep: LiveData<Boolean> = _toTheNextStep

    private val _user = MutableLiveData<User>(
        User(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    )
    val user: LiveData<User> = _user

    fun onSelectGender(gender: Gender) {
        _toTheNextStep.value = true
        _userGender.value = gender
    }

    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        _toTheNextStep.value = !s.isNullOrBlank()
    }

    fun onClickNextFragment(fragment: SignUpFragment, next: Boolean) {
        if (next) {
            _signUpFragment.value = fragment
        }
    }

    fun onClickPopFragment() {
        _popFragment.value = Unit
    }

    fun onClickUserAgreement(context: Context) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.notion.so/ahobsu/MOTI-35d01dd331bb4aa0915c33d28d60b63c")
            )
        )
    }

    fun setUserInfo(name: String?, gender: String?, birthday: String?) {
        user.value?.let {
            _user.value = User(
                it.id,
                birthday ?: it.birthday,
                it.email,
                name ?: it.name,
                gender ?: it.gender,
                it.refreshDate,
                it.refreshToken,
                it.mission,
                it.snsId,
                it.snsType,
                it.createdAt,
                it.updatedAt
            )
        }
    }

}