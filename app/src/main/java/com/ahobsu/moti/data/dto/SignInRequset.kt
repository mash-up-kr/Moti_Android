package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class SignInRequset(
    @SerializedName("snsType")
    val snsType: String?
)