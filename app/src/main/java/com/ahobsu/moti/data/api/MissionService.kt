package com.ahobsu.moti.data.api

import com.ahobsu.moti.data.dto.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MissionService {
    @GET("missions")
    fun getMissions(): Single<BaseData<MissionResponse>>

    @GET("missions/refresh")
    fun getRefreshMissions(): Single<BaseData<MissionResponse>>
}