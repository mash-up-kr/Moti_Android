package com.ahobsu.moti.domain.repository

import com.ahobsu.moti.domain.entity.Mission
import io.reactivex.rxjava3.core.Single

interface MissionRepository {
    fun getMissionId(id:Int): Single<Mission>
    fun getMissions(): Single<List<Mission>>
    fun getRefreshMissions(): Single<List<Mission>>
}