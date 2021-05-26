package com.ahobsu.moti.domain.repository

import com.ahobsu.moti.domain.entity.Answer
import com.ahobsu.moti.domain.entity.AnswersWeek
import io.reactivex.rxjava3.core.Single

interface AnswerRepository {
    fun postAnswer(answer: Answer): Single<Boolean>
    fun getAnswersWeek(): Single<AnswersWeek>
}