package com.ahobsu.moti.data.source.mission

import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.data.dto.Mission
import com.ahobsu.moti.data.dto.MissionsResponse
import io.reactivex.rxjava3.core.Single

interface MissionDataSource {
    fun getMissionId(id:Int): Single<BaseData<Mission>>
    fun getMissions(): Single<BaseData<MissionsResponse>>
    fun getRefreshMissions(): Single<BaseData<MissionsResponse>>
}