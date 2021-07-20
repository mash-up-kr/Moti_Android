package com.ahobsu.moti.domain.entity

data class AnswersWeek(
    val answers: List<MissionCard>?
)

data class MissionCard(
    val answerId: Int?,
    val missionId: Int?,
    val cardPart: Int?,
    val cardPngUrl: String?
)