package com.ahobsu.moti.data.source.user

import android.util.Log
import com.ahobsu.moti.domain.Result
import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {

//    override  fun getUser(): Result<User> {
//        val a =userDataSource.postSignIn("snsType")
//        when (a) {
//            is Result.Success -> {
//                a.data.data
//
//            }
//        }
//    }

    override  fun signIn(snsType: String): Result<Unit> {
        Log.e("123123", "signIn")
        val a = userDataSource.postSignIn(snsType)

        when (a) {
            is Result.Success -> {
                Log.e("123123",  a.data.toString())
                a.data.data?.accessToken?.let { Log.e("123123", it) }
                a.data.data?.accessToken
            }
            is Result.Error -> {
                Log.e("123123",  "error")

                return Result.Error(Exception("Refresh failed"))
            }
            else -> throw IllegalStateException()
        }

        return Result.Error(Exception("Error fetching from remote and local"))

    }
}