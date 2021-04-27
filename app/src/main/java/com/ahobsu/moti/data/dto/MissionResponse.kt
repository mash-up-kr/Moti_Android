package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class MissionResponse(
    @SerializedName("refresh")
    val refresh: Boolean?,
    @SerializedName("missions")
    val missions: List<Missions>?
)