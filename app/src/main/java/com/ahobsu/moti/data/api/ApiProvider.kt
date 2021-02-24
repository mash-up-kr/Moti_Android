package com.ahobsu.moti.data.api

import android.util.Log
import com.ahobsu.moti.MotiApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    private const val baseUrl = "https://moti.company/api/v1/"

    private val authToken: String?
        get() = MotiApplication.INSTANCE.sharedPreferences.getString("jwt", null)

    fun provideSignInApi(): SignInService = getRetrofitBuild.create(SignInService::class.java)

    private var client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder().apply {
                authToken?.let {
                    Log.e("Authorization", it)
                    addHeader("Authorization", it)
                }
            }.build()
            chain.proceed(newRequest)
        }.build()

    private val getRetrofitBuild =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(client)
            // 받은 응답을 옵서버블 형태로 변환해 줍니다.
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}