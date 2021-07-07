package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class AnswerRequest(
    @SerializedName("content")
    val content: String?,
    @SerializedName("missionId")
    val missionId: Int?,
    @SerializedName("file")
    val file: MultipartBody.Part?
)