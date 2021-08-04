package com.ahobsu.moti.data.source.answer

import com.ahobsu.moti.data.dto.AnswersDiaryRequest
import com.ahobsu.moti.domain.entity.Answer
import com.ahobsu.moti.domain.entity.AnswersDiary
import com.ahobsu.moti.domain.entity.AnswersWeek
import com.ahobsu.moti.domain.entity.MissionCard
import com.ahobsu.moti.domain.repository.AnswerRepository
import io.reactivex.rxjava3.core.Single

class AnswerRepositoryImpl(private val answerDataSource: AnswerDataSource) : AnswerRepository {

    override fun postAnswer(answer: Answer): Single<Boolean> {
        return answerDataSource.postAnswer(answer).map { res ->
            res.status == 200
        }
    }

    override fun getAnswersWeek(): Single<AnswersWeek> {
        return answerDataSource.getAnswersWeek().map { res ->
            res.data?.let {
                AnswersWeek(
                    it.answers?.map { answer ->
                        MissionCard(
                            answerId = answer.id,
                            missionId = answer.missionId,
                            cardPart = answer.file?.part,
                            cardPngUrl = answer.file?.cardPngUrl
                        )
                    })
            }
        }
    }

    override fun getAnswersList(): Single<List<AnswersWeek>> {
        return answerDataSource.getAnswersList().map { res ->
            val albumList = emptyList<AnswersWeek>().toMutableList()
            res.data?.let {
                for (i in it)
                    albumList += AnswersWeek(
                        i.map { answer ->
                            MissionCard(
                                answerId = answer.id,
                                missionId = answer.missionId,
                                cardPart = answer.file?.part,
                                cardPngUrl = answer.file?.cardPngUrl
                            )
                        })
                albumList
            }
        }
    }

    override fun getAnswersAlbumItemList(id: Int): Single<List<AnswersDiary>> {
        return answerDataSource.getAnswersAlbumItemList(id).map { res ->
            res.data?.let { data ->
                data.map {
                    AnswersDiary(
                        date = it.date,
                        answerId = it.id,
                        missionId = it.missionId,
                        imageUrl = it.imageUrl,
                        title = it.mission?.title,
                        content = it.content,
                        isContent = it.mission?.isContent,
                        isImage = it.mission?.isImage
                    )
                }

            }
        }
    }

    override fun getAnswersDiary(direction: Int?, limit: Int?, date: String?): Single<List<AnswersDiary>> {
        return answerDataSource.getAnswersDiary(
            AnswersDiaryRequest(direction = direction, limit = limit, date = date)
        ).map { res ->
            res.data?.let { data ->
                data.answers?.map {
                    AnswersDiary(
                        date = it.date,
                        answerId = it.id,
                        missionId = it.missionId,
                        imageUrl = it.imageUrl,
                        title = it.mission?.title,
                        content = it.content,
                        isContent = it.mission?.isContent,
                        isImage = it.mission?.isImage
                    )
                }
            }
        }
    }

    override fun getAnswersDays(): Single<List<String>> {
        return answerDataSource.getAnswersDays().map { res ->
            res.data?.let {
                it
            }
        }
    }

    override fun getAnswerToday(): Single<Boolean> {
        return answerDataSource.getAnswersWeek().map { res ->
            res.data?.let { it ->
                !it.answers?.filter { answer ->
                    answer.date == it.today
                }.isNullOrEmpty()
            }
        }
    }


}

