package com.ahobsu.moti.presentation.ui.splash

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.VideoView
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

    enum class ActivityType { LOGIN, MAIN }

    private var startActivityType: ActivityType = ActivityType.LOGIN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startNextActivity()

        val videoView = findViewById<View>(R.id.video_view_splash) as VideoView
        val video: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.splash)
        videoView.setVideoURI(video)
        videoView.start()

        videoView.setOnCompletionListener { startActivity() }
    }

    private fun startActivity() {
        when (startActivityType) {
            ActivityType.LOGIN -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            ActivityType.MAIN -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        finish()
    }

    private fun startNextActivity() {
        if (Unit.accessToken.isNullOrEmpty()) {
            startActivityType = ActivityType.LOGIN
        } else {
            login()
        }
    }

    private fun login() {
        UserUseCase(Injection.provideUserRepository()).getUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.name.isNullOrEmpty()) {
                    startActivityType = ActivityType.LOGIN
                } else {
                    startActivityType = ActivityType.MAIN
                }
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
                startActivityType = ActivityType.MAIN
            }, { _ ->
                startActivityType = ActivityType.LOGIN
            })
    }
}