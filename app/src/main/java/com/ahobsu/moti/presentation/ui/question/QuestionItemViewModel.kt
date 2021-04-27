package com.ahobsu.moti.presentation.ui.question


data class QuestionItemViewModel(
    val id: Int? = 0,
    val title: String? = "",
    val isContent: Boolean? = true,
    val isImage: Boolean? = false
)