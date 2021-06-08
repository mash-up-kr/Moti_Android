package com.ahobsu.moti.domain.entity

data class User(
    val id: Int?,
    val birthday: String?,
    val email: String?,
    val name: String?,
    val gender: String?,
    val refreshDate: String?,
    val refreshToken: String?,
    val mission: String?,
    val snsId: String?,
    val snsType: String?,
    val createdAt: String?,
    val updatedAt: String?
)