package com.ahobsu.moti.domain

import android.util.Log
import com.ahobsu.moti.domain.repository.UserRepository

class SiginInUseCase(
    private val userRepository: UserRepository,
    private val snsType: String
) {
    operator fun invoke(): Result<Unit> {
        return userRepository.signIn(snsType)
    }
}