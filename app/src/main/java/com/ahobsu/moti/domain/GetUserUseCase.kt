package com.ahobsu.moti.domain

import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.domain.repository.UserRepository

class GetUserUseCase (
    private val userRepository: UserRepository
) {
//     operator fun invoke(): Result<User> {
//        return userRepository.getUser()
//    }
}