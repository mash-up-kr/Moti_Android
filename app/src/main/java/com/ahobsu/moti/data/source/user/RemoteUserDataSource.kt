package com.ahobsu.moti.data.source.user

import android.util.Log
import com.ahobsu.moti.data.api.UserService
import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.data.dto.SignInRequset
import com.ahobsu.moti.data.dto.SignInResponse
import com.ahobsu.moti.data.dto.UserMyResponse
import com.ahobsu.moti.domain.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteUserDataSource(
    private val userApi: UserService
) : UserDataSource {

    override fun postSignIn(snsType: String): Single<BaseData<SignInResponse>> {
        val signInRequest = SignInRequset(snsType = snsType)
        return userApi.postSignIn(signInRequest)
    }

    override fun postSignInRefresh(): Single<BaseData<SignInResponse>> {
        return userApi.postSignInRefresh()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserMy(): Single<BaseData<UserMyResponse>> {
        return userApi.getUserMy()
    }
}