package com.ahobsu.moti.data.api

import com.ahobsu.moti.data.dto.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface AnswerService {
    @Multipart
    @POST("answers")
    fun postAnswer(
        @Part("content")  content: String?,
        @Part("missionId") missionId: Int?,
        @Part("file") file: String?
    ): Single<BaseData<Unit>>

    @GET("answers/week")
    fun getAnswersWeek(): Single<BaseData<AnswersWeekResponse>>

}