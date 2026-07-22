package com.powakaz.nesttrack.feature_time.pres.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.powakaz.nesttrack.feature_time.R
import com.powakaz.nesttrack.feature_time.pres.components.ActivitiesItem
import com.powakaz.nesttrack.feature_time.pres.components.UserAvatar

private val shape20 = RoundedCornerShape(20.dp)


@Composable
fun TimeTrackingScreen(
    viewModel: TimeTrackingScreenViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TimeTrackingScreenContent(uiState)
}


@Composable
fun TimeTrackingScreenContent(uiState: TimeTrackingUiState) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.time_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            ShowTimeBalance(uiState)
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))

            TextButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2156FE))

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_add_24),
                    contentDescription = null,
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier.size(26.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(id = R.string.btn_add_concession),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))

            ShowActivities()
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.your_concession),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(28.dp),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(10.dp))

        }

        val items = listOf("One", "Two", "Two", "Two", "Two", "Two", "Two")
        items(items) { item ->
            ShowListConcession()
        }



    }
}

@Composable
fun ShowListConcession() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 2.dp,
                shape = shape20
            )
            .background(Color(0xFFFDFDFD))
            .clip(shape20),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Spacer(modifier = Modifier.width(10.dp))

        ActivitiesItem(
            modifier = Modifier
                .width(36.dp)
                .height(36.dp),
            icon = painterResource(id = R.drawable.bus),
            backgroundColor = Color(0xFFD2FCD5),
            shape = CircleShape
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.widthIn(max = 90.dp)
        ) {
            Text(
                text = "Велосипед",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Сегодня, 18:00",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                fontFamily = FontFamily.SansSerif
            )

        }

        VerticalDivider(
            modifier = Modifier
                .padding(start = 8.dp)
                .height(40.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )

        Column(
            modifier = Modifier
                .widthIn(max = 100.dp)
                .padding(start = 6.dp)
        ) {
            Text(
                text = "2 ч 00 мин",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Покатались по лесу",
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                fontFamily = FontFamily.SansSerif,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        UserAvatar(
            icon = painterResource(id = R.drawable.man),
            size = (30.dp),
            modifier = Modifier
        )

        Spacer(modifier = Modifier.width(2.dp))

        Icon(
            painter = painterResource(id = R.drawable.outline_arrow_forward_24),
            contentDescription = null,
            modifier = Modifier.size(14.dp)
        )

        Spacer(modifier = Modifier.width(2.dp))

        UserAvatar(
            icon = painterResource(id = R.drawable.girl),
            size = (30.dp),
            modifier = Modifier
        )

        Spacer(modifier = Modifier.width(2.dp))

        IconButton(
            onClick = {},
            modifier = Modifier
                .size(24.dp)
                .padding(2.dp)
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.ellipsis),
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.width(2.dp))

    }

    Spacer(modifier = Modifier.height(4.dp))

}

@Composable
fun ShowActivities() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 2.dp,
                shape = shape20
            )
            .background(Color(0xFFFDFDFD))
            .clip(shape20)

    ) {

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.your_activities),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.offset(14.dp)
            )

            Text(
                text = stringResource(id = R.string.control),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2156FE),
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .offset((-14).dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


        val items = listOf("One", "Two", "Two", "Two", "Two", "Two", "Two", "Two", "Two", "Two", "Two", "Two", "Two")

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(horizontal = 14.dp)
        ) {

            item {
                AddNewActivity()
            }

            items(items) { item ->
                ActivitiesItem(
                    text = "Велосипед",
                    modifier = Modifier.width(50.dp),
                    icon = painterResource(id = R.drawable.bus),
                    backgroundColor = Color(0xFFC5FFCA),
                    shape = RoundedCornerShape(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

    }
}

@Composable
fun AddNewActivity() {
    Column(
        modifier = Modifier.width(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFEAEAEA))
                .padding(6.dp),
            contentDescription = null,
            painter = painterResource(id = R.drawable.outline_add_24),
            tint = Color.Unspecified,
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(id = R.string.add),
            textAlign = TextAlign.Center,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun ShowTimeBalance(uiState: TimeTrackingUiState) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 4.dp,
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

    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(200.dp)
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd)
        )
        {

            Image(
                painter = painterResource(id = R.drawable.scales),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterEnd)
                    .padding(10.dp)
            )

            UserAvatar(
                icon = painterResource(id = R.drawable.man),
                size = (40.dp),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 18.dp, y = 10.dp)
            )

            UserAvatar(
                icon = painterResource(id = R.drawable.girl),
                size = (40.dp),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = (-20).dp, y = (-14).dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 30.dp)
        ) {
            Text(
                text = stringResource(id = R.string.you_balance),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = uiState.timeBalance,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2156FE),
                fontFamily = FontFamily.Default,
                maxLines = 1
            )
            //Log.e("LOL", uiState.timeBalance)

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

    }
}


@Preview(showBackground = true)
@Composable
fun TimeTrackingScreenPreview() {
    TimeTrackingScreenContent(uiState = TimeTrackingUiState())
}