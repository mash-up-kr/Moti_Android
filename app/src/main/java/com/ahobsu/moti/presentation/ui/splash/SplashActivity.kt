package com.ahobsu.moti.presentation.ui.splash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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

    private lateinit var mPreferences: SharedPreferences

    //    private val access_token = "accessToken"
    private val sharedPrefFile = "app_preferences"

//    private val accessToken: String?
//        get() = MotiApplication.INSTANCE.sharedPreferences.getString("≈", null)
//
//    private val refreshToken: String?
//        get() = MotiApplication.INSTANCE.sharedPreferences.getString("refreshToken", null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Unit.mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        if (Unit.accessToken.isNullOrEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            login()
        }


        Log.e("accessToken", "  ${Unit.accessToken}")
        Log.e("refreshToken", "  ${Unit.refreshToken}")


        //TODO:: 1. accessToken 조회,, 없으면 로그인화면, 있으면 그대로 유저정보 조회   0
        //TODO:: 2. 1에서 유저정보 조회 실패할 경우 refreshToken있는지 조회 없으면 로그인화 있으면 accessToken 값을
        // refreshToken로 바꿔서 재시
        //TODO::도 3. 실패시 로그인 화면
    }

    private fun login() {
        UserUseCase(Injection.provideUserRepository()).getUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("getUserMy", "success  $it")
                startActivity(Intent(this, MainActivity::class.java))
            }, { e ->
                refreshLogin()
            })
    }

    private fun refreshLogin() {
        SignInUseCase(Injection.provideUserRepository()).refreshLogin()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("getUserMy", "success  $it")
                Unit.putAccessToken(it.accessToken)
                Unit.putRefreshToken(it.refreshToken)
                startActivity(Intent(this, MainActivity::class.java))
            }, { e ->
                startActivity(Intent(this, LoginActivity::class.java))

            })
    }
}