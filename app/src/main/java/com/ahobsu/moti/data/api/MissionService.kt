package com.ahobsu.moti.data.api

import com.ahobsu.moti.data.dto.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MissionService {
    @GET("missions/{id}")
    fun getMissionId(
        @Path("id") id: Int
    ): Single<BaseData<Mission>>

    @GET("missions")
    fun getMissions(): Single<BaseData<MissionsResponse>>

    @GET("missions/refresh")
    fun getRefreshMissions(): Single<BaseData<MissionsResponse>>
}