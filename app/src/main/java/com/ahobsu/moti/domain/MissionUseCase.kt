package com.ahobsu.moti.domain

import com.ahobsu.moti.domain.entity.Mission
import com.ahobsu.moti.domain.repository.MissionRepository
import io.reactivex.rxjava3.core.Single

class MissionUseCase(private val missionRepository: MissionRepository) {
    fun getMissionId(id:Int): Single<Mission> {
        return missionRepository.getMissionId(id)
    }

    fun getMissions(): Single<List<Mission>> {
        return missionRepository.getMissions()
    }
    fun getRefreshMissions(): Single<List<Mission>> {
        return missionRepository.getRefreshMissions()
    }
}