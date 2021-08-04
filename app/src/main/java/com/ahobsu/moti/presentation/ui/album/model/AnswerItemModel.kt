package com.ahobsu.moti.presentation.ui.album.model

class AnswerItemModel(
    val id: Int,
    val date: String,
    val title: String = "",
    val content: String = "",
    val imageUrl: String?,
    val isContent: Boolean = true,
    val isImage: Boolean = false
)