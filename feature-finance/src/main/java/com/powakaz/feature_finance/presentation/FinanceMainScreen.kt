package com.powakaz.feature_finance.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.powakaz.feature_finance.R

@Composable
fun FinanceMainScreenRoute() {

}


@Preview
@Composable
fun FinanceMainScreenPreview() {
    FinanceMainScreen()
}


@Composable
fun FinanceMainScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            TopBar()
            Head()
            Wallets()
            QuickActions()
        }
    }

}

@Composable
fun TopBar() {
    Text(
        text = "Финансы",
        color = Color(0XFF071145),
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 16.dp)
    )
    Text(
        text = "Баланс, кошельки и операции",
        color = Color(0XFF747a9f),
        fontSize = 12.sp,
        modifier = Modifier.padding(start = 16.dp, top = 2.dp)
    )
}


@Composable
fun Head() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(16.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0XFFf1f5fd),
                            Color(0xFFfceefe)
                        )
                    )
                )
        ) {
            Row() {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 20.dp, bottom = 20.dp)
                        .fillMaxWidth(0.45f)
                ) {
                    Text(
                        text = "Общий баланс",
                        color = Color(0XFF10173f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Br 2 000",
                        color = Color(0XFF147afd),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    HorizontalDivider(
                        color = Color(0XFFe3eafc),
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )
                    Text(
                        text = "Недельный бюджет",
                        color = Color(0XFF10173f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(modifier = Modifier.padding(top = 6.dp, bottom = 10.dp)) {
                        Text(
                            text = "Br 150",
                            color = Color(0XFF147afd),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "осталось",
                            color = Color(0XFF69709b),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(start = 4.dp, bottom = 1.dp)
                                .align(Alignment.Bottom)
                        )
                    }
                    FinanceHeadProgressBar(0.3f)
                }
                Image(
                    painter = painterResource(R.drawable.img_wallet),
                    contentDescription = null,
                    modifier = Modifier.align(
                        Alignment.CenterVertically
                    )
                )
            }
        }
    }
}

@Composable
fun FinanceHeadProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .background(color = Color(0XFFd9e0fb), shape = RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier
                .padding(start = 1.dp)
                .fillMaxHeight()
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .background(color = Color(0XFF0e6afb), shape = RoundedCornerShape(16.dp))
        )
    }

}


@Composable
fun Wallets() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Мои кошельки",
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically),
            color = Color(0XFF071145),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Все кошельки",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
                color = Color(0XFF076ffe),
                fontSize = 12.sp
            )
            Image(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0XFF076ffe)),
                modifier = Modifier.size(14.dp)
            )
        }
    }

    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 16.dp, end = 4.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0XFFf1f8f1))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_cash),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp, top = 8.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = "Наличные",
                            color = Color(0XFF071145),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp
                        )
                        Row() {
                            Text(
                                text = "Br 200",
                                modifier = Modifier
                                    .padding(top = 4.dp, bottom = 4.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                painter = painterResource(R.drawable.ic_arrow_right),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color(0XFF85bf92))
                            )
                        }
                        Text(text = "Кошелек", color = Color(0XFF69709b), fontSize = 11.sp)
                    }
                }
            }

        }


        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, start = 4.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0XFFedf5fd))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_card),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp, top = 8.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = "Безналичные",
                            color = Color(0XFF071145),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp
                        )
                        Row() {
                            Text(
                                text = "Br 200",
                                modifier = Modifier
                                    .padding(top = 4.dp, bottom = 4.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                painter = painterResource(R.drawable.ic_arrow_right),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color(0XFF1072fe))
                            )
                        }
                        Text(text = "Кошелек", color = Color(0XFF69709b), fontSize = 11.sp)
                    }
                }
            }

        }
    }
}


@Composable
fun QuickActions() {
    Text(
        text = "Быстрые действия",
        modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 8.dp),
        color = Color(0XFF071145),
        fontWeight = FontWeight.SemiBold
    )
    Row(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        QuicAction(
            "Расход",
            Color(0xFFFC665B),
            R.drawable.ic_arrow_down,
            modifier = Modifier.weight(1f)
        )
        QuicAction("Доход",
            Color(0XFF50ae68),
            R.drawable.ic_plus,
            modifier = Modifier.weight(1f))
        QuicAction(
            "Перевод",
            Color(0XFF9650fc),
            R.drawable.ic_double_arrows,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun QuicAction(text: String, color: Color, iconId: Int, modifier: Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.5.dp
        ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp, bottom = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(shape = CircleShape, color = color)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(iconId),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(
                text = text,
                color = Color(0XFF071145),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}