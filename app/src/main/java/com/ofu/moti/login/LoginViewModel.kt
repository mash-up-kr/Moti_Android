package com.ofu.moti.login

import androidx.lifecycle.MutableLiveData
import com.ofu.moti.BaseViewModel

class LoginViewModel :BaseViewModel(){

    enum class SignUpFragment {Birthday,Complete,Gender,NickName,EXIT}

    val signUpFragment:MutableLiveData<SignUpFragment> = MutableLiveData()

    fun onClickNextFragment(fragment:SignUpFragment){
        signUpFragment.postValue(fragment)
    }
}