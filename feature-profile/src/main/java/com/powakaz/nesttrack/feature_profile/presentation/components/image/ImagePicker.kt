package com.powakaz.nesttrack.feature_profile.presentation.components.image

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.powakaz.nesttrack.feature_profile.presentation.utils.createImageUri

@Composable
fun ImagePicker(
    onImagePicked:(Uri) -> Unit,
    content: @Composable (openCamera: () -> Unit,
                          openGallery: () -> Unit) -> Unit
) {
    val context = LocalContext.current

    var cameraUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            cameraUri?.let(onImagePicked) //cameraUri?.let(viewModel::onAvatarSelected)
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let(onImagePicked)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            cameraUri = context.createImageUri()
            cameraLauncher.launch(cameraUri!!)
        }
    }

    fun openCamera() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            cameraUri = context.createImageUri()
            cameraLauncher.launch(cameraUri!!)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    fun openGallery() {
        galleryLauncher.launch("image/*")
    }


    content({ openCamera() }, { openGallery() })

}