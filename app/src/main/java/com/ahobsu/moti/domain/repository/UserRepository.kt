package com.ahobsu.moti.domain.repository

import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.data.dto.SignInResponse
import com.ahobsu.moti.data.dto.UserMyResponse
import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.domain.Result
import io.reactivex.rxjava3.core.Single

interface UserRepository {
     fun signIn(snsType:String): Single<BaseData<SignInResponse>>
     fun refreshSignIn(): Single<BaseData<SignInResponse>>
     fun getUser(): Single<BaseData<UserMyResponse>>
}