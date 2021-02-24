package com.ahobsu.moti

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class MotiApplication : Application() {

    companion object {
        lateinit var INSTANCE: MotiApplication
    }

    val sharedPreferences: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            "app_preferences",
            AppCompatActivity.MODE_PRIVATE
        )
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}