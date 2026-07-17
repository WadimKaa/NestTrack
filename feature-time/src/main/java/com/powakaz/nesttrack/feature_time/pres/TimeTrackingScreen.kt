package com.powakaz.nesttrack.feature_time.pres

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.powakaz.nesttrack.feature_time.R

private val shape20 = RoundedCornerShape(20.dp)


@Composable
fun TimeTrackingScreen() {
    TimeTrackingScreenContent()
}


@Composable
fun TimeTrackingScreenContent() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ///item

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.time_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif
        )

        ///item

        Spacer(modifier = Modifier.height(20.dp))
        ShowTimeBalance()

        ///item
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(60.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2156FE))

        ) {
            Text(
                text = stringResource(id = R.string.btn_add_concession),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontFamily = FontFamily.SansSerif
            )
        }


    }
}

@Composable
fun ShowTimeBalance() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 8.dp,
                shape = shape20
            )
            .clip(shape20)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFEEF3FD),
                        Color(0xFFFEF2FB)
                    )
                )
            )
            .border(
                2.dp,
                Color.White,
                shape20
            )
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp, top = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.you_balance),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "+ 3 ч 25 мин",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2156FE),
                fontFamily = FontFamily.Default,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.you_owe_time), // R.string._you_owe_time
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.target),
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF8F95B2),
                fontFamily = FontFamily.SansSerif
            )
        }

        Row (
            modifier = Modifier
                .width(200.dp)
                .align(Alignment.CenterEnd)
        )
        {

            Image(
                painter = painterResource(id = R.drawable.man),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
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
            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.girl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
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

    }
}


@Preview(showBackground = true)
@Composable
fun TimeTrackingScreenPreview() {
    TimeTrackingScreenContent()
}