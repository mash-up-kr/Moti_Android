package com.ahobsu.moti.data.source.answer

import android.util.Log
import com.ahobsu.moti.data.api.AnswerService
import com.ahobsu.moti.data.dto.AnswerRequest
import com.ahobsu.moti.data.dto.AnswersWeekResponse
import com.ahobsu.moti.data.dto.BaseData
import com.ahobsu.moti.domain.entity.Answer
import io.reactivex.rxjava3.core.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class RemoteAnswerDataSource(
    private val answerService: AnswerService
) : AnswerDataSource {

    override fun postAnswer(answer: Answer): Single<BaseData<Unit>> {
        val file = File(answer.file?.path)

        var fileName = answer.file.toString().replace("@", "").replace(".", "")
        fileName = "$fileName.png"

        var requestBody: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        var imageBody: MultipartBody.Part = MultipartBody.Part.createFormData("uploaded_file", fileName, requestBody)

        val answerRequest = AnswerRequest(
            content = answer.content,
            missionId = answer.missionId,
            file = imageBody
        )

        //file=@logo_stacked 3.png;type=image/png'
        Log.e("postAnswer", answerRequest.toString())
        return answerService.postAnswer(answerRequest.content, answerRequest.missionId, answerRequest.file)
    }

    override fun getAnswersWeek(): Single<BaseData<AnswersWeekResponse>> {
        return  answerService.getAnswersWeek()
    }

}