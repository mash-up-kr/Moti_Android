package com.ahobsu.moti.data.source.user

import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.data.dto.SignInResponse
import com.ahobsu.moti.data.dto.UserMyResponse
import com.ahobsu.moti.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun signIn(snsType: String): Single<BaseData<SignInResponse>> {
        return userDataSource.postSignIn(snsType)
    }

    override fun refreshSignIn(): Single<BaseData<SignInResponse>> {
        return userDataSource.postSignInRefresh()
    }

    override fun getUser(): Single<BaseData<UserMyResponse>> {
        return userDataSource.getUserMy()
            .subscribeOn(Schedulers.io())
    }
}