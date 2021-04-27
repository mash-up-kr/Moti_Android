package com.ahobsu.moti.domain

import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.data.dto.SignInResponse
import com.ahobsu.moti.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class SigInInUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(snsType: String): Single<BaseData<SignInResponse>> {
        return userRepository.signIn(snsType)
    }

    fun refreshLogin(): Single<BaseData<SignInResponse>> {
        return userRepository.refreshSignIn()
    }
}