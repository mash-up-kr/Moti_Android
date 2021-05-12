package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class AnswerRequest(
    @SerializedName("content")
    val content: String?,
    @SerializedName("missionId")
    val missionId: Int,
    @SerializedName("file")
    val file: String?
)