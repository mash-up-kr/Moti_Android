package com.ahobsu.moti.data.source.user

import android.util.Log
import com.ahobsu.moti.data.api.SignInService
import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.data.dto.SignInRequset
import com.ahobsu.moti.data.dto.SignInResponse
import com.ahobsu.moti.domain.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteUserDataSource(
    private val signInApi: SignInService
) : UserDataSource {

    override fun postSignIn(snsType: String): Result<BaseData<SignInResponse>>? {
        Log.e("123123", "postSignIn")

        val signInRequest = SignInRequset(snsType = snsType)

        signInApi.postSignIn2(signInRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("postSignIn", "success signUp $it")
            }, { e ->
                Log.e("postSignIn e", e.toString())
            })

        return try {
            Result.Success(signInApi.postSignIn(signInRequest))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun postSignInRefresh(): Single<BaseData<SignInResponse>> {
        return signInApi.postSignInRefresh()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}