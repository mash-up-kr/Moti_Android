package com.ahobsu.moti.domain.entity


data class AnswersWeek(
    val today: Boolean,
    val answers: List<MissionCard>?

)

data class MissionCard(
    val missionId: Int?,
    val cardPart: Int?,
    val cardPngUrl: String?
)