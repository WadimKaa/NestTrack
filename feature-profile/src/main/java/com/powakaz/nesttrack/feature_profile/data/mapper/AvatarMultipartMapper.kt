package com.powakaz.nesttrack.feature_profile.data.mapper

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class AvatarMultipartMapper @Inject constructor() {
    fun map(path: String): MultipartBody.Part {
        val file = File(path)

        val requestBody = file.asRequestBody("image/*".toMediaType())

        return MultipartBody.Part.createFormData(
            name = "avatar",
            filename = file.name,
            body = requestBody
        )
    }
}