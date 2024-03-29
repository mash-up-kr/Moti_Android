package com.ahobsu.moti.presentation.ui.diary.model

data class DiaryItemModel(
    val answerId: Int,
    val dayOfWeek: String,
    val date: String,
    val days: String,
    val month: String,
    val year: String,
    val title: String = "",
    val content: String = "",
    val imageUrl: String?,
    val isContent: Boolean = true,
    val isImage: Boolean = false,
    var isLastMonthItem: Boolean = false
) {
    fun getYearMonth(): String {
        return "$year.$month"
    }
}