package com.ahobsu.moti.data.api

import com.ahobsu.moti.data.dto.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("signin")
    fun postSignIn(@Body requestBody: SignInRequset): BaseData<SignInResponse>

    @POST("signin")
    fun postSignIn2(@Body requestBody: SignInRequset): Single<BaseData<SignInResponse>>

    @POST("signin/refresh")
    fun postSignInRefresh(): Single<BaseData<SignInResponse>>
}