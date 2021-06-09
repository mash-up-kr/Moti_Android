package com.ahobsu.moti.presentation.ui.diary.model

data class DiaryItemModel(
    val id: Int,
    val days: String,
    val month: String,
    val title: String = "",
    val content: String = "",
    val imageUrl: String?,
    val isContent: Boolean = true,
    val isImage: Boolean = false
)