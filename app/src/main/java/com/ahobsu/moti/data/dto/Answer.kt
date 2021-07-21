package com.ahobsu.moti.data.dto

import com.google.gson.annotations.SerializedName

data class Answer(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("no")
    val no: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("missionId")
    val missionId: Int?,
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("fileId")
    val fileId: Int?,
    @SerializedName("file")
    val file: AnswerFile?,
    @SerializedName("mission")
    val mission: AnswerMission?
)

data class AnswerFile(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("part")
    val part: Int?,
    @SerializedName("cardPngUrl")
    val cardPngUrl: String?
)

data class AnswerMission(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("part")
    val part: Int?,
    @SerializedName("isContent")
    val isContent: Boolean?,
    @SerializedName("isImage")
    val isImage: Boolean?,
    @SerializedName("title")
    val title: String?
)