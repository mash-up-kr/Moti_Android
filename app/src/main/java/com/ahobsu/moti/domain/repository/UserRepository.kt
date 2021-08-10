package com.ahobsu.moti.domain.repository

import android.net.Uri
import com.ahobsu.moti.domain.entity.SignIn
import com.ahobsu.moti.domain.entity.User
import io.reactivex.rxjava3.core.Single

interface UserRepository {
     fun signIn(snsType: String): Single<SignIn>
     fun refreshSignIn(): Single<SignIn>
     fun getUser(): Single<User>
     fun putUserInfo(user: User): Single<User>
     fun putUserProfile(userProfile: Uri): Single<String>
     fun deleteUser(): Single<String>
}