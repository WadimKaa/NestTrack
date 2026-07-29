package com.powakaz.nesttrack.feature_time.pres.components

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.powakaz.nesttrack.feature_time.R


@Composable
fun ActivitiesItem(
    text: String? = null,
    modifier: Modifier = Modifier,
    icon: Painter,
    backgroundColor: Color,
    shape: Shape,
    tint: Color,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    ActivitiesItemContent(text, modifier, icon, backgroundColor, shape, tint, isSelected,
        onClick)
}

@Composable
fun ActivitiesItemContent(text: String? = null,
                          modifier: Modifier,
                          icon: Painter,
                          backgroundColor: Color,
                          shape: Shape,
                          tint: Color,
                          isSelected: Boolean = false,
                          onClick: (() -> Unit)? = null)
{


    Column(
        modifier = modifier
            .then(
                if (onClick != null) {
                    Modifier.clickable {
                        onClick() ////
                    }
                } else {
                    Modifier
                }
            )
            .border(
                width = if (isSelected) 1.dp else 0.dp,
                color = if (isSelected) Color(0xFFA17CDE) else Color.Transparent,
                shape = RoundedCornerShape(12.dp),
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .size(40.dp)
                .clip(shape)
                .background(backgroundColor)
                .padding(6.dp),
            contentDescription = null,
            painter = icon,
            tint = tint,
        )

        text?.let {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = it,
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivitiesItemPreview() {
    ActivitiesItemContent(
        text = "Велосипед",
        modifier = Modifier.width(60.dp),
        icon = painterResource(id = R.drawable.bus),
        Color(0xFFC5FFCA),
        shape = RoundedCornerShape(16.dp),
        tint = Color(0xFF1F6725),
    )
}