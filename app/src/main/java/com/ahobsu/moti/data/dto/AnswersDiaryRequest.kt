package com.ahobsu.moti.data.dto

import android.net.Uri
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class AnswersDiaryRequest(
    @SerializedName("direction")
    val direction: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("date")
    val date: String?
)