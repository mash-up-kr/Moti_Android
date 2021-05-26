package com.ahobsu.moti.data.source.answer

import com.ahobsu.moti.domain.entity.Answer
import com.ahobsu.moti.domain.entity.AnswersWeek
import com.ahobsu.moti.domain.entity.MissionCard
import com.ahobsu.moti.domain.repository.AnswerRepository
import io.reactivex.rxjava3.core.Single

class AnswerRepositoryImpl(
    private val answerDataSource: AnswerDataSource
) : AnswerRepository {

    override fun postAnswer(answer: Answer): Single<Boolean> {
        return answerDataSource.postAnswer(answer).map { res ->
            res.status == 200
        }
    }

    override fun getAnswersWeek(): Single<AnswersWeek> {
        return answerDataSource.getAnswersWeek().map { res ->
            res.data?.let {
                var today = false
                AnswersWeek(
                    today, it.answers?.map { answer ->
                    if (answer.date == it.today) {
                        today = true
                    }
                    MissionCard(
                        answer.missionId,
                        answer.file?.part,
                        answer.file?.cardPngUrl
                    )
                })
            }
        }
    }
}