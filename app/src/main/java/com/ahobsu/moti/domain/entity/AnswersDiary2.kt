package com.ahobsu.moti.domain.entity

data class AnswersDiary2(
    val day: String,
    val month: String,
    val year: String,
    val answerId: Int?,
    val missionId: Int?,
    val imageUrl: String?,
    val title: String?,
    val content: String?,
    val isContent: Boolean?,
    val isImage: Boolean?
)
