package com.ahobsu.moti.data

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

import java.io.File

object FormDataUtil {
    fun getBody(key: String, value: Any?): MultipartBody.Part? {
        if (value == null) return null
        return MultipartBody.Part.createFormData(key, value.toString())
    }

    fun getImageBody(key: String, file: File?): MultipartBody.Part? {
        if (file == null) return null
        return MultipartBody.Part.createFormData(
            name = key,
            filename = file.name,
            body = file.asRequestBody("image/*".toMediaType())
        )
    }
}