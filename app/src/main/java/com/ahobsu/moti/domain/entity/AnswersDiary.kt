package com.ahobsu.moti.domain.entity

data class AnswersDiary(
    val year: Int?,
    val month: Int?,
    val diaryItems: ArrayList<DiaryItem>?
)

data class DiaryItem(
    val answerId: Int?,
    val missionId: Int?,
    val imageUrl: String?,
    val title: String?,
    val content: String?,
    val date: String?,
    val isContent: Boolean?,
    val isImage: Boolean?
)