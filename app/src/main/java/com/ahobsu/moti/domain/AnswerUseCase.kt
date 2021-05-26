package com.ahobsu.moti.domain

import com.ahobsu.moti.domain.entity.Answer
import com.ahobsu.moti.domain.entity.AnswersWeek
import com.ahobsu.moti.domain.repository.AnswerRepository
import io.reactivex.rxjava3.core.Single

class AnswerUseCase(
    private val answerRepository: AnswerRepository
) {
    fun postAnswer(answer: Answer): Single<Boolean> {
            return answerRepository.postAnswer(answer)
    }
    fun getAnswersWeek(): Single<AnswersWeek> {
        return answerRepository.getAnswersWeek()
    }
}