package com.powakaz.nesttrack.feature_profile.presentation.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun Context.createImageUri(): Uri {
    val imageFile = File(
        filesDir,
        "images/avatar.jpg" //_${System.currentTimeMillis()}
    )

    imageFile.parentFile?.mkdirs()

    return FileProvider.getUriForFile(
        this,
        "$packageName.fileprovider",
        imageFile
    )
}