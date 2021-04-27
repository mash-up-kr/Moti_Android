package com.ahobsu.moti

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

object Unit {
    private val access_token = "accessToken"
    private val refresh_token = "refreshToken"
    private val jwt_token = "jwt"
    lateinit var mPreferences: SharedPreferences

    val accessToken: String?
        get() = MotiApplication.INSTANCE.sharedPreferences.getString(access_token, null)

    val refreshToken: String?
        get() = MotiApplication.INSTANCE.sharedPreferences.getString(refresh_token, null)

    val jwt: String?
        get() = MotiApplication.INSTANCE.sharedPreferences.getString(jwt_token, null)


    fun putAccessToken(accessToken: String?) {
        accessToken?.let {
            val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
            preferencesEditor.putString(access_token, it)
            preferencesEditor.apply()
        }

    }

    fun putRefreshToken(refreshToken: String?) {
        refreshToken?.let {
            val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
            preferencesEditor.putString(refresh_token, it)
            preferencesEditor.apply()
        }

    }

    fun putJWT(jwt: String?) {
        jwt?.let {
            val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
            preferencesEditor.putString(jwt_token, it)
            preferencesEditor.apply()
        }
    }
}
