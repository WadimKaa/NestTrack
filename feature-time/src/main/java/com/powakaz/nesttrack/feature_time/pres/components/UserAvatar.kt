package com.powakaz.nesttrack.feature_time.pres.components

import androidx.annotation.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.powakaz.nesttrack.feature_time.R


@Composable
fun UserAvatar(
    icon: Painter,
    size: Dp,
    modifier: Modifier,
) {
    UserAvatarContent(icon, size, modifier)
}

@Composable
fun UserAvatarContent(icon: Painter, size: Dp, modifier: Modifier) {


    Image(
        painter = icon,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFFEDF0FF),
                        Color(0xFFB7C1FA)
                    )
                )
            )
            .border(
                2.dp,
                Color.White,
                CircleShape
            )

    )
}

@Preview(showBackground = true)
@Composable
fun UserAvatarPreview() {
    UserAvatarContent(
        icon = painterResource(id = R.drawable.man),
        size = (40.dp),

        modifier = Modifier
    )
}

