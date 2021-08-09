package com.ahobsu.moti.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahobsu.moti.R
import com.ahobsu.moti.Unit
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.domain.SignInUseCase
import com.ahobsu.moti.domain.UserUseCase
import com.ahobsu.moti.presentation.ui.login.LoginActivity
import com.ahobsu.moti.presentation.ui.main.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Unit.accessToken.isNullOrEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            login()
        }
    }

    private fun login() {
        UserUseCase(Injection.provideUserRepository()).getUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.name.isNullOrEmpty()) {
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                finish()
            }, { _ ->
                refreshLogin()
            })
    }

    private fun refreshLogin() {
        SignInUseCase(Injection.provideUserRepository()).refreshLogin()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Unit.putAccessToken(it.accessToken)
                Unit.putRefreshToken(it.refreshToken)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, { _ ->
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            })
    }
}