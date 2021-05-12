package com.ahobsu.moti.data.source.mission

import com.ahobsu.moti.domain.entity.Mission
import com.ahobsu.moti.domain.repository.MissionRepository
import io.reactivex.rxjava3.core.Single

class MissionRepositoryImpl(
    private val missionDataSource: MissionDataSource
) : MissionRepository {
    override fun getMissionId(id: Int): Single<Mission> {
        return missionDataSource.getMissionId(id).map { res ->
            res.data?.let {
                Mission(
                    id = it.id,
                    title = it.title,
                    isContent = it.isContent,
                    isImage = it.isImage,
                    cycle = it.cycle
                )
            }
        }
    }

    override fun getMissions(): Single<List<Mission>> {
        return missionDataSource.getMissions().map { res ->
            res.data?.missions?.map {
                Mission(
                    id = it.id,
                    title = it.title,
                    isContent = it.isContent,
                    isImage = it.isImage,
                    cycle = it.cycle
                )
            }
        }
    }

    override fun getRefreshMissions(): Single<List<Mission>> {
        return missionDataSource.getRefreshMissions().map { res ->
            res.data?.missions?.map {
                Mission(
                    id = it.id,
                    title = it.title,
                    isContent = it.isContent,
                    isImage = it.isImage,
                    cycle = it.cycle
                )
            }
        }
    }
}