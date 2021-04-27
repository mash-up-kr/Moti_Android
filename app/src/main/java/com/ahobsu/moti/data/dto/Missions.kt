package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class Missions(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("isContent")
    val isContent: Boolean?,
    @SerializedName("isImage")
    val isImage: Boolean?,
    @SerializedName("cycle")
    val cycle: Int?,
    @SerializedName("createdAt")
    val createdAt: Boolean?,
    @SerializedName("updatedAt")
    val updatedAt: Boolean?
)