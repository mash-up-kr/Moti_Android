package com.ahobsu.moti.domain.entity

data class User(
    val id :Int,
    val birthday :String = "1997-01-16",
    val email :String = "yuchochpie@gmail.com",
    val name :String = "유정",
    val gender :String = "여",
    val refreshDate :String = "null",
    val refreshToken :String = "null",
    val mission :String = "null",
    val snsId :String = "1",
    val snsType :String = "google",
    val createdAt :String = "2020-01-03T15:27:18.000Z",
    val updatedAt :String = "2020-01-03T16:52:18.000Z"
)