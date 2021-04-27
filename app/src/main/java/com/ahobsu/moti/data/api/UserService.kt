package com.ahobsu.moti.data.api

import com.ahobsu.moti.data.dto.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("signin")
    fun postSignIn(@Body requestBody: SignInRequset):Single<BaseData<SignInResponse>>

    @POST("signin/refresh")
    fun postSignInRefresh(): Single<BaseData<SignInResponse>>

    @GET("users/my")
    fun getUserMy(): Single<BaseData<UserMyResponse>>
}