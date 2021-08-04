package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class UserMyResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("refreshDate")
    val refreshDate: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?,
    @SerializedName("mission")
    val mission: String?,
    @SerializedName("snsId")
    val snsId: String?,
    @SerializedName("snsType")
    val snsType: String?,
    @SerializedName("profileUrl")
    val profileUrl: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)