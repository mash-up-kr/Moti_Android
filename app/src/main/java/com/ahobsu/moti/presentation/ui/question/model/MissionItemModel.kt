package com.ahobsu.moti.presentation.ui.question.model


data class MissionItemModel(
    val id: Int = 0,
    val missionNum: Int = 0,
    val title: String = "",
    val isContent: Boolean = true,
    val isImage: Boolean = false
) {
    fun  getMissionNum():String{
        return "질문 $missionNum"
    }

}