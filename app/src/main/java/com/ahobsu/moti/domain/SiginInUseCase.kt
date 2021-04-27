package com.ahobsu.moti.domain

import com.ahobsu.moti.domain.entity.SignIn
import com.ahobsu.moti.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class SigInInUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(snsType: String): Single<SignIn> {
        return userRepository.signIn(snsType)
    }

    fun refreshLogin(): Single<SignIn> {
        return userRepository.refreshSignIn()
    }
}