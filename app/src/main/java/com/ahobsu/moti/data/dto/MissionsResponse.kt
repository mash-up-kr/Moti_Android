package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class MissionsResponse(
    @SerializedName("refresh")
    val refresh: Boolean?,
    @SerializedName("missions")
    val missions: List<Mission>?
)