package com.powakaz.nesttrack.feature_time.pres.components.items

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.powakaz.nesttrack.feature_time.R
import com.powakaz.nesttrack.feature_time.domain.model.avatar.Avatar


@Composable
fun UserAvatar(
    icon: String,
    size: Dp,
    modifier: Modifier,
) {
    UserAvatarContent(icon, size, modifier)
}

@Composable
fun UserAvatarContent(icon: String, size: Dp, modifier: Modifier) {


    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Color.White)
            .border(
                1.dp,
                Color(0xFF835EFF),
                CircleShape
            )

    ) {
        AsyncImage(
            model = icon,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_walk_activities),
            error = painterResource(R.drawable.ic_walk_activities),
            modifier = Modifier.fillMaxSize(),
            onError = { state ->
                Log.e("AvatarError", "Failed to load image: ${state.result.throwable}") },
            onSuccess = { Log.d("AvatarError", "Image loaded successfully!")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserAvatarPreview() {
    UserAvatarContent(
        icon = "",
        size = (40.dp),
        modifier = Modifier
    )
}

