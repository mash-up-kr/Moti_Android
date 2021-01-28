package com.ahobsu.moti.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.ahobsu.moti.BaseViewModel

class LoginViewModel :BaseViewModel() {

    enum class SignUpFragment { Birthday, Complete, Gender, NickName, EXIT }
    enum class Gender { MAN, WOMAN }

    val signUpFragment: MutableLiveData<SignUpFragment> = MutableLiveData()
    val popFragment = MutableLiveData<Unit>()
    val etNickname = MutableLiveData<String>()
    val userGender = MutableLiveData<Gender>()
    val toTheNextStep = MutableLiveData<Boolean>(false)


    fun onSelectGender(gender: Gender) {
        toTheNextStep.postValue(true)
        userGender.postValue(gender)
    }

    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        toTheNextStep.postValue(!s.isNullOrBlank())
    }

    fun onClickNextFragment(fragment: SignUpFragment, next: Boolean) {
        if (next) {
            signUpFragment.postValue(fragment)
        }
    }

    fun onClickPopFragment() {
        popFragment.postValue(Unit)
    }

    fun onClickUserAgreement(context: Context) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.notion.so/ahobsu/MOTI-35d01dd331bb4aa0915c33d28d60b63c")
            )
        )
    }

}