package com.ahobsu.moti.presentation.ui.question.model

import android.net.Uri

data class AnswerModel(
    val content: String?,
    val missionId: Int,
    val file: Uri?
)