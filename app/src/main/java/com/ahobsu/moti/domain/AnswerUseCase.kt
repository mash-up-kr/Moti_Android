package com.ahobsu.moti.domain

import com.ahobsu.moti.domain.entity.Answer
import com.ahobsu.moti.domain.entity.AnswersDiary
import com.ahobsu.moti.domain.entity.AnswersDiary2
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

    fun getAnswersList(): Single<List<AnswersWeek>> {
        return answerRepository.getAnswersList()
    }

    fun getAnswerToday(): Single<Boolean> {
        return answerRepository.getAnswerToday()
    }

    fun getAnswersDiary2(direction: Int?, limit: Int?, date: String?): Single<List<AnswersDiary2>> {
        return answerRepository.getAnswersDiary2(direction, limit, date)
    }

    fun getAnswersDiary(limit: Int, date: String): Single<List<AnswersDiary>> {
        return answerRepository.getAnswersDiary(limit, date)
    }

    fun getAnswersDays(): Single<List<String>> {
        return answerRepository.getAnswersDays()
    }

}