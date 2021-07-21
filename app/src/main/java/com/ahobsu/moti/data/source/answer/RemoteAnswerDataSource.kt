package com.ahobsu.moti.data.source.answer

import com.ahobsu.moti.data.FormDataUtil
import com.ahobsu.moti.data.api.AnswerService
import com.ahobsu.moti.data.dto.*
import com.ahobsu.moti.domain.entity.Answer
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import java.io.File


class RemoteAnswerDataSource(
    private val answerService: AnswerService
) : AnswerDataSource {

    override fun postAnswer(answer: Answer): Single<BaseData<Unit>> {
        val answerRequest = AnswerRequest(
            content = answer.content,
            missionId = answer.missionId,
            file = answer.file
        )
        val formContent = FormDataUtil.getBody("content", answerRequest.content)
        val formMissionId = FormDataUtil.getBody("missionId", answerRequest.missionId)
        var formFile: MultipartBody.Part? = null
        answer.file?.let {
            formFile = FormDataUtil.getImageBody("file", File(it.path))
        }

        return answerService.postAnswer(
            formContent,
            formMissionId,
            formFile
        )
    }

    override fun getAnswersWeek(): Single<BaseData<AnswersWeekResponse>> {
        return answerService.getAnswersWeek()
    }

    override fun getAnswersList(): Single<BaseData<List<List<com.ahobsu.moti.data.dto.Answer>>?>> {
        return answerService.getAnswersList()
    }

    override fun getAnswersDiary(diaryRequest: AnswersDiaryRequest): Single<BaseData<AnswersDiaryResponse>> {
        return answerService.getAnswersDiary(diaryRequest.direction, diaryRequest.limit,
            diaryRequest.date)
    }

    override fun getAnswersDays(): Single<BaseData<List<String>>> {
        return answerService.getAnswersDays()
    }
}