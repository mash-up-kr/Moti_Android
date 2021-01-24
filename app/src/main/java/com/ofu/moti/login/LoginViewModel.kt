package com.ofu.moti.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ofu.moti.BaseViewModel

class LoginViewModel :BaseViewModel() {

    enum class SignUpFragment { Birthday, Complete, Gender, NickName, EXIT }

    val signUpFragment: MutableLiveData<SignUpFragment> = MutableLiveData()
    val popFragment = MutableLiveData<Unit>()
    val etNickname = MutableLiveData<String>()
    val toTheNextStep = MutableLiveData<Boolean>(false)

    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        toTheNextStep.postValue(!s.isNullOrBlank())
    }
    fun onClickNextFragment(fragment: SignUpFragment) {
        signUpFragment.postValue(fragment)
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