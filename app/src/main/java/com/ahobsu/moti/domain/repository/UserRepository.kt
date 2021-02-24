package com.ahobsu.moti.domain.repository

import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.domain.Result

interface UserRepository {
//     fun getUser(): Result<User>

     fun signIn(snsType:String): Result<Unit>
}