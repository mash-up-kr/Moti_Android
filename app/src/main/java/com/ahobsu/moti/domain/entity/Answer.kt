package com.ahobsu.moti.domain.entity

import android.net.Uri

data class Answer(
    val content: String?,
    val missionId: Int?,
    val file: Uri?
)