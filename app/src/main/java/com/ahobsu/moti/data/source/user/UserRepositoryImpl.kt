package com.ahobsu.moti.data.source.user

import android.net.Uri
import com.ahobsu.moti.domain.entity.SignIn
import com.ahobsu.moti.domain.entity.User
import com.ahobsu.moti.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun signIn(snsType: String): Single<SignIn> {
        return userDataSource.postSignIn(snsType).map { signInResponse ->
            signInResponse.data?.let {
                SignIn(
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken,
                    signUp = it.signUp
                )
            }
        }
    }

    override fun refreshSignIn(): Single<SignIn> {
        return userDataSource.postSignInRefresh().map { signInResponse ->
            signInResponse.data?.let {
                SignIn(
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken,
                    signUp = it.signUp
                )
            }
        }
    }

    override fun getUser(): Single<User> {
        return userDataSource.getUserMy().map { base ->
            base.data?.let {
                User(
                    id = it.id,
                    birthday = it.birthday,
                    email = it.email,
                    name = it.name,
                    gender = it.gender,
                    refreshDate = it.refreshDate,
                    refreshToken = it.refreshToken,
                    mission = it.mission,
                    snsId = it.snsId,
                    snsType = it.snsType,
                    profileUrl = it.profileUrl,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt
                )
            }

        }
    }

    override fun putUserInfo(user: User): Single<User> {
        return userDataSource.putUserInfo(user).map { base ->
            base.data?.let {
                User(
                    id = it.id,
                    birthday = it.birthday,
                    email = it.email,
                    name = it.name,
                    gender = it.gender,
                    refreshDate = it.refreshDate,
                    refreshToken = it.refreshToken,
                    mission = it.mission,
                    snsId = it.snsId,
                    snsType = it.snsType,
                    profileUrl = it.profileUrl,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt
                )
            }

        }
    }

    override fun putUserProfile(userProfile: Uri): Single<String> {
        return userDataSource.putUserProfileImage(userProfile).map {
            it.data?.let {
                "Success"
            }
        }
    }

    override fun deleteUser(): Single<String> {
        return userDataSource.deleteUser().map {
            if (it.status == 200) {
                "Success"
            } else "error"
        }
    }

}