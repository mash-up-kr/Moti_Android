package com.ahobsu.moti.data.api

import android.util.Log
import com.ahobsu.moti.Unit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    private const val baseUrl = "https://moti.company/api/v1/"

    fun provideAnswerApi(): AnswerService = getRetrofitBuild.create(AnswerService::class.java)
    fun provideMissionApi(): MissionService = getRetrofitBuild.create(MissionService::class.java)
    fun provideSignInApi(): UserService = getRetrofitBuild.create(UserService::class.java)
    fun provideSignUpApi(): UserService = getSignUpRetrofitBuild.create(UserService::class.java)

    private var clientSignUp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder().apply {
                Unit.jwt?.let {
                    Log.e("Authorization", it)
                    addHeader("Authorization", it)
                }
            }.build()
            chain.proceed(newRequest)
        }.build()

    private val getSignUpRetrofitBuild =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(clientSignUp)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private var clientSignIn = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder().apply {
                Unit.accessToken?.let {
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
            .client(clientSignIn)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}