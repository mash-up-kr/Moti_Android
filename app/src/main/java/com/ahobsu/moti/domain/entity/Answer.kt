package com.ahobsu.moti.domain.entity

data class SignIn(
    val accessToken: String = "",
    val refreshToken: String = "",
    val signUp: Boolean = false
)