package com.ahobsu.moti.domain

import android.util.Log
import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class UserUseCase (
    private val userRepository: UserRepository
) {
    fun getUser(): Single<User>  {
        return userRepository.getUser()
    }
    fun putUserInfo(user:User): Single<User> {
        Log.e("234","user")
        return userRepository.putUserInfo(user)
    }
//     operator fun invoke(): Result<User> {
//        return userRepository.getUser()
//    }
}