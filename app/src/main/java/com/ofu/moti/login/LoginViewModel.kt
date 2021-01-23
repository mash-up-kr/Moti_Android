package com.ofu.moti.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.ofu.moti.BaseViewModel

class LoginViewModel :BaseViewModel() {

    enum class SignUpFragment { Birthday, Complete, Gender, NickName, EXIT }

    val signUpFragment: MutableLiveData<SignUpFragment> = MutableLiveData()

    fun onClickNextFragment(fragment: SignUpFragment) {
        signUpFragment.postValue(fragment)
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