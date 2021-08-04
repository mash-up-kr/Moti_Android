package com.ahobsu.moti.domain

import android.net.Uri
import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class UserUseCase (
    private val userRepository: UserRepository
) {
    fun getUser(): Single<User> {
        return userRepository.getUser()
    }

    fun putUserInfo(user: User): Single<User> {
        return userRepository.putUserInfo(user)
    }

    fun putUserProfile(userProfile: Uri): Single<String> {
        return userRepository.putUserProfile(userProfile)
    }
}