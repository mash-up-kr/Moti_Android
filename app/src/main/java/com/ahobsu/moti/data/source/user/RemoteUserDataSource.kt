package com.ahobsu.moti.data.source.user

import android.net.Uri
import com.ahobsu.moti.data.FormDataUtil
import com.ahobsu.moti.data.api.UserService
import com.ahobsu.moti.data.dto.*
import com.ahobsu.moti.domain.entity.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import java.io.File

class RemoteUserDataSource(
    private val userApi: UserService
) : UserDataSource {

    override fun getUserMy(): Single<BaseData<UserMyResponse>> {
        return userApi.getUserMy()
    }

    override fun postSignIn(snsType: String): Single<BaseData<SignInResponse>> {
        val signInRequest = SignInRequset(snsType = snsType)
        return userApi.postSignIn(signInRequest)
    }

    override fun postSignInRefresh(): Single<BaseData<SignInResponse>> {
        return userApi.postSignInRefresh()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun putUserInfo(user: User): Single<BaseData<UserMyResponse>> {
        return userApi.putUserInfo(UserInfoResquset(user.name, user.birthday, user.gender))
    }

    override fun putUserProfileImage(userProfileImage: Uri): Single<BaseData<Unit>> {
        var formFile: MultipartBody.Part? = null
        userProfileImage?.let {
            formFile = FormDataUtil.getImageBody("file", File(it.path))
        }
        return userApi.putUserProfileImage(formFile)
    }

    override fun deleteUser(): Single<BaseData<Unit?>> {
        return userApi.deleteUser()
    }
}