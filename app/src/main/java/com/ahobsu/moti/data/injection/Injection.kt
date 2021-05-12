package com.ahobsu.moti.data.injection

import com.ahobsu.moti.data.api.ApiProvider
import com.ahobsu.moti.data.source.answer.AnswerDataSource
import com.ahobsu.moti.data.source.answer.AnswerRepositoryImpl
import com.ahobsu.moti.data.source.answer.RemoteAnswerDataSource
import com.ahobsu.moti.data.source.mission.MissionDataSource
import com.ahobsu.moti.data.source.mission.MissionRepositoryImpl
import com.ahobsu.moti.data.source.mission.RemoteMissionDataSource
import com.ahobsu.moti.data.source.user.RemoteUserDataSource
import com.ahobsu.moti.data.source.user.UserDataSource
import com.ahobsu.moti.data.source.user.UserRepositoryImpl
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.domain.repository.MissionRepository
import com.ahobsu.moti.domain.repository.UserRepository

object Injection {

    fun provideAnswerRepository(): AnswerRepository {
        return AnswerRepositoryImpl(provideAnswerRemoteDataSource())
    }

    private fun provideAnswerRemoteDataSource(): AnswerDataSource {
        return RemoteAnswerDataSource(ApiProvider.provideAnswerApi())
    }

    fun provideMissionRepository(): MissionRepository {
        return MissionRepositoryImpl(provideMissionRemoteDataSource())
    }

    private fun provideMissionRemoteDataSource(): MissionDataSource {
        return RemoteMissionDataSource(ApiProvider.provideMissionApi())
    }

    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(provideUserRemoteDataSource())
    }

    private fun provideUserRemoteDataSource(): UserDataSource {
        return RemoteUserDataSource(ApiProvider.provideSignInApi())
    }

    fun provideSignUpRepository(): UserRepository {
        return UserRepositoryImpl(provideSignUpRemoteDataSource())
    }

    private fun provideSignUpRemoteDataSource(): UserDataSource {
        return RemoteUserDataSource(ApiProvider.provideSignUpApi())
    }
}