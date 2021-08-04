package com.ahobsu.moti.domain.repository

import com.ahobsu.moti.domain.entity.Answer
import com.ahobsu.moti.domain.entity.AnswersDiary
import com.ahobsu.moti.domain.entity.AnswersWeek
import io.reactivex.rxjava3.core.Single

interface AnswerRepository {
    fun postAnswer(answer: Answer): Single<Boolean>
    fun getAnswersWeek(): Single<AnswersWeek>
    fun getAnswerToday(): Single<Boolean>
    fun getAnswersList(): Single<List<AnswersWeek>>
    fun getAnswersItemList(id:Int): Single<List<AnswersDiary>>
    fun getAnswersDiary(direction:Int?, limit:Int?, date:String?): Single<List<AnswersDiary>>
    fun getAnswersDays(): Single<List<String>>
}