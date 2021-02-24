package com.ahobsu.moti.data.source.user

import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.data.dto.SignInResponse
import com.ahobsu.moti.domain.Result
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    fun postSignIn(snsType: String): Result<BaseData<SignInResponse>>?
    fun postSignInRefresh(): Single<BaseData<SignInResponse>>
}