package com.ahobsu.moti.data.api

import com.ahobsu.moti.data.dto.*
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface UserService {
    @POST("signin")
    fun postSignIn(@Body requestBody: SignInRequset): Single<BaseData<SignInResponse>>

    @POST("signin/refresh")
    fun postSignInRefresh(): Single<BaseData<SignInResponse>>

    @PUT("users")
    fun putUserInfo(@Body requestBody: UserInfoResquset): Single<BaseData<UserMyResponse>>

    @GET("users/my")
    fun getUserMy(): Single<BaseData<UserMyResponse>>

    @Multipart
    @PUT("users/my/profile")
    fun putUserProfileImage(
        @Part file: MultipartBody.Part?
    ): Single<BaseData<Unit>>

    @DELETE("users")
    fun deleteUser(): Single<BaseData<Unit?>>

}