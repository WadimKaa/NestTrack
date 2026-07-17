package com.powakaz.nesttrack.feature_profile.presentation.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun Context.createImageUri(): Uri {

    val fileName = "temp_camera_${System.currentTimeMillis()}.jpg"

    val imageFile = File(
        filesDir,
        "images/$fileName"
    )

    imageFile.parentFile?.mkdirs()

    return FileProvider.getUriForFile(
        this,
        "$packageName.fileprovider",
        imageFile
    )
}

fun Context.copyImageToAppStorage(uri: Uri): String {

    deleteOldFiles()

    val fileName = "avatar_${System.currentTimeMillis()}.jpg"

    val imageFile = File(
        filesDir,
        "images/$fileName"
    )

    imageFile.parentFile?.mkdirs()

    contentResolver.openInputStream(uri)?.use { input ->
        imageFile.outputStream().use { output ->
            input.copyTo(output)
        }
    } ?: error("Не удалось открыть изображение")

    return imageFile.absolutePath
}


private fun Context.deleteOldFiles() {
    val folder = File(
        filesDir,
        "images"
    )

    if (folder.exists()) {
        folder.listFiles()?.forEach { file ->
            if (file.name.startsWith("avatar_")) {
                file.delete()
            }
        }
    }

}