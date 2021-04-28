package com.ahobsu.moti.data.source.mission

import com.ahobsu.moti.data.api.MissionService
import com.ahobsu.moti.data.dto.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteMissionDataSource(
    private val missionApi: MissionService
) : MissionDataSource {
    override fun getMissionId(id: Int): Single<BaseData<Mission>> {
        return missionApi.getMissionId(id)
    }

    override fun getMissions(): Single<BaseData<MissionsResponse>> {
        return missionApi.getMissions()
    }

    override fun getRefreshMissions(): Single<BaseData<MissionsResponse>> {
        return missionApi.getRefreshMissions()
    }
}