package com.ahobsu.moti.data.source.answer

import android.util.Log
import com.ahobsu.moti.data.api.AnswerService
import com.ahobsu.moti.data.dto.AnswerRequest
import com.ahobsu.moti.data.dto.AnswersWeekResponse
import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.domain.entity.Answer
import io.reactivex.rxjava3.core.Single

class RemoteAnswerDataSource(
    private val answerService: AnswerService
) : AnswerDataSource {

    override fun postAnswer(answer: Answer): Single<BaseData<Unit>> {
        val answerRequest = AnswerRequest(
            content = answer.content,
            missionId = answer.missionId,
            file = answer.file
        )
        Log.e("postAnswer",answerRequest.toString())
        return answerService.postAnswer(answerRequest.content,answerRequest.missionId, answerRequest.file)
    }

    override fun getAnswersWeek(): Single<BaseData<AnswersWeekResponse>> {
        return  answerService.getAnswersWeek()
    }

}