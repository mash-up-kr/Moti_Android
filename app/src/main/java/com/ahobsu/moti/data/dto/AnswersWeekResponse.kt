package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class AnswersWeekResponse(
    @SerializedName("content")
    val today: String?,
    @SerializedName("answers")
    val answers: List<Answer>?
)
