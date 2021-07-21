package com.ahobsu.moti.data.source.answer

import com.ahobsu.moti.data.dto.AnswersDiaryRequest
import com.ahobsu.moti.domain.entity.*
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

    override fun getAnswersList(): Single<AnswersWeek> {
        return answerDataSource.getAnswersList().map { res ->
            res.data?.let {
                AnswersWeek(
                    it[0].map { answer ->
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

    override fun getAnswersDiary(limit: Int, date: String): Single<List<AnswersDiary>> {
        return answerDataSource.getAnswersDiary(
            AnswersDiaryRequest(direction = 0, limit = limit, date = date)).map { res ->
            res.data?.let { data ->
                var diaryList: List<AnswersDiary> = emptyList()
                data.answers?.forEach {
                    val dateSplit = it.date?.split("-")
                    val year = dateSplit?.get(0)?.toInt()
                    val month = dateSplit?.get(1)?.toInt()

                    if (diaryList.isEmpty()) {
                        diaryList = diaryList + AnswersDiary(year, month, arrayListOf(getDiaryItem(it)))
                    } else {
                        var saved = false
                        for (i in diaryList.indices) {
                            if (diaryList[i].year == year && diaryList[i].month == month) {
                                diaryList[i].diaryItems?.add(
                                    getDiaryItem(it)
                                )
                                saved = true
                                break
                            }
                        }
                        if (!saved) {
                            diaryList = diaryList + AnswersDiary(year, month, arrayListOf(getDiaryItem(it)))
                        }
                    }
                }
                diaryList
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

    private fun getDiaryItem(it: com.ahobsu.moti.data.dto.Answer): DiaryItem {
        return DiaryItem(
            answerId = it.id,
            missionId = it.missionId,
            imageUrl = it.imageUrl,
            title = it.mission?.title,
            content = it.content,
            date = it.date,
            isContent = it.mission?.isContent,
            isImage = it.mission?.isImage
        )
    }


}