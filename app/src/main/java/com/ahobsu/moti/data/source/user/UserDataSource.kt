package com.ahobsu.moti.data.source.user

import android.net.Uri
import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.data.dto.SignInResponse
import com.ahobsu.moti.data.dto.UserMyResponse
import com.ahobsu.moti.domain.entity.User
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    fun postSignIn(snsType: String): Single<BaseData<SignInResponse>>
    fun postSignInRefresh(): Single<BaseData<SignInResponse>>
    fun getUserMy(): Single<BaseData<UserMyResponse>>
    fun putUserInfo(userInfo: User): Single<BaseData<UserMyResponse>>
    fun putUserInfo(userProfileImage: Uri): Single<BaseData<Unit>>
}