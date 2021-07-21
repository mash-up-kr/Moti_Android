package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class AnswersDiaryResponse(
    @SerializedName("date")
    val date: String?,
    @SerializedName("answers")
    val answers: List<Answer>?
)
