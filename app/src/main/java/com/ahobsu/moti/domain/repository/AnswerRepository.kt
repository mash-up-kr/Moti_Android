package com.ahobsu.moti.domain.repository

import com.ahobsu.moti.data.dto.AnswersDiaryRequest
import com.ahobsu.moti.domain.entity.Answer
import com.ahobsu.moti.domain.entity.AnswersDiary
import com.ahobsu.moti.domain.entity.AnswersDiary2
import com.ahobsu.moti.domain.entity.AnswersWeek
import io.reactivex.rxjava3.core.Single

interface AnswerRepository {
    fun postAnswer(answer: Answer): Single<Boolean>
    fun getAnswersWeek(): Single<AnswersWeek>
    fun getAnswerToday(): Single<Boolean>
    fun getAnswersList(): Single<AnswersWeek>
    fun getAnswersDiary(limit:Int,date:String): Single<List<AnswersDiary>>
    fun getAnswersDiary2(direction:Int?,limit:Int?,date:String?): Single<List<AnswersDiary2>>
    fun getAnswersDays(): Single<List<String>>
}