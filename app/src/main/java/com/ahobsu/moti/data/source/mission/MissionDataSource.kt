package com.ahobsu.moti.data.source.mission

import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.data.dto.MissionResponse
import com.ahobsu.moti.data.dto.SignInResponse
import com.ahobsu.moti.data.dto.UserMyResponse
import io.reactivex.rxjava3.core.Single

interface MissionDataSource {
    fun getMissions(): Single<BaseData<MissionResponse>>
}