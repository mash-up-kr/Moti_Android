package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class UserInfoResquset(
    @SerializedName("name")
    val name: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("gender")
    val gender: String?
)