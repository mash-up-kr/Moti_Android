package com.ahobsu.moti.data.api

import com.ahobsu.moti.data.dto.AnswersWeekResponse
import com.ahobsu.moti.data.dto.BaseData
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface AnswerService {

    @Multipart
    @POST("answers")
    fun postAnswer(
        @Part content: MultipartBody.Part?,
        @Part missionId: MultipartBody.Part?,
        @Part file: MultipartBody.Part?
    ): Single<BaseData<Unit>>


    @GET("answers/week")
    fun getAnswersWeek(): Single<BaseData<AnswersWeekResponse>>

}