package com.ahobsu.moti.data.api

import com.ahobsu.moti.data.dto.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AnswerService {
    @POST("answers")
    fun postAnswer(@Body answer: AnswerRequest): Single<BaseData<Unit>>
}