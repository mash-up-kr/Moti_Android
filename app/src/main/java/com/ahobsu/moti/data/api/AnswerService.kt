package com.ahobsu.moti.data.api

import com.ahobsu.moti.data.dto.*
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*


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

    @GET("answers/list")
    fun getAnswersList(): Single<BaseData<List<List<Answer>>?>>

    @GET("answers/list/{id}")
    fun getAnswersItemList(
        @Path("id") id: Int
    ): Single<BaseData<List<Answer>>>


    @GET("answers/diary")
    fun getAnswersDiary(
        @Query("direction") direction: Int?,
        @Query("limit") limit: Int?,
        @Query("date") date: String?
    ): Single<BaseData<AnswersDiaryResponse>>

    @GET("answers/days")
    fun getAnswersDays(): Single<BaseData<List<String>>>
}