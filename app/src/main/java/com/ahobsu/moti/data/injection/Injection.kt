package com.ahobsu.moti.data.injection

import android.util.Log
import com.ahobsu.moti.data.api.ApiProvider
import com.ahobsu.moti.data.source.user.UserDataSource
import com.ahobsu.moti.data.source.user.UserRepositoryImpl
import com.ahobsu.moti.data.source.user.RemoteUserDataSource
import com.ahobsu.moti.domain.repository.UserRepository

object Injection {

//    fun provideSignInRepository():SignInDataSource{
//        return RemoteUserDataSource(
//            ApiProvider.provideSignInApi()
//        )
//    }



    fun provideUserRepository(): UserRepository {
        Log.e("123123", "provideUserRepository")

        return UserRepositoryImpl(provideUserRemoteDataSource())
    }

    private fun provideUserRemoteDataSource(): UserDataSource {
        Log.e("123123", "provideUserRemoteDataSource")

        return RemoteUserDataSource(ApiProvider.provideSignInApi())
    }
}