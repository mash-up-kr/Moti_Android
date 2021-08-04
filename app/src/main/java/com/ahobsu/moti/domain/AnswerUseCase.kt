package com.ahobsu.moti.domain

import com.ahobsu.moti.domain.entity.Answer
import com.ahobsu.moti.domain.entity.AnswersDiary
import com.ahobsu.moti.domain.entity.AnswersWeek
import com.ahobsu.moti.domain.repository.AnswerRepository
import io.reactivex.rxjava3.core.Single

class AnswerUseCase(
    private val answerRepository: AnswerRepository
) {
    fun postAnswer(answer: Answer): Single<Boolean> {
        return answerRepository.postAnswer(answer)
    }

    fun getAnswerItem(id: Int): Single<AnswersDiary> {
        return answerRepository.getAnswerItem(id)
    }

    fun getAnswersWeek(): Single<AnswersWeek> {
        return answerRepository.getAnswersWeek()
    }

    fun getAnswersList(): Single<List<AnswersWeek>> {
        return answerRepository.getAnswersList()
    }

    fun getAnswersAlbumItemList(id:Int): Single<List<AnswersDiary>>  {
        return answerRepository.getAnswersAlbumItemList(id)
    }

    fun getAnswerToday(): Single<Boolean> {
        return answerRepository.getAnswerToday()
    }

    fun getAnswersDiary(direction: Int?, limit: Int?, date: String?): Single<List<AnswersDiary>> {
        return answerRepository.getAnswersDiary(direction, limit, date)
    }

    fun getAnswersDays(): Single<List<String>> {
        return answerRepository.getAnswersDays()
    }

}