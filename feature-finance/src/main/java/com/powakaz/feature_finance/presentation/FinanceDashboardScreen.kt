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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.powakaz.feature_finance.R
import com.powakaz.feature_finance.domain.model.WalletType
import com.powakaz.feature_finance.presentation.model.FinanceDayUiState
import com.powakaz.feature_finance.presentation.model.TransactionUiState

@Composable
fun FinanceDashboardScreenRoute(viewModel: FinanceDashboardViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    FinanceDashboardScreen(state)
}


@Preview
@Composable
fun FinanceDashboardScreenPreview() {
    FinanceDashboardScreen(FinDashboardUiState())
}




@Composable
fun FinanceDashboardScreen(uiState: FinDashboardUiState) {
    Scaffold { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                TopBar()
            }
            item {
                Head(uiState.userBalance, uiState.weekBalance, uiState.progressWeekBalance)
            }
            item {
                Wallets(uiState.cashBalance, uiState.cardBalance)
            }
            item {
                QuickActions()
            }
            item {
                Text(
                    text = "Операции по дням",
                    modifier = Modifier
                        .padding(start = 16.dp, top = 12.dp),
                    color = Color(0XFF071145),
                    fontWeight = FontWeight.SemiBold
                )
            }
            items(items = uiState.listDays, key = { it.id}) {
                OneDayCard(it)
            }
            item { Spacer(modifier = Modifier.height(4.dp)) }
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
fun Head(userTotalBalance: String, weekBalance: String, weekProgress: Float) {
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
                        text = "$userTotalBalance BYN",
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
                            text = "$weekBalance BYN",
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
                    FinanceHeadProgressBar(weekProgress)
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
fun Wallets(cashBalance: String, cardBalance: String) {
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
                modifier = Modifier
                    .size(14.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }

    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        WalletCard(
            cashBalance,
            "Наличные",
            R.drawable.ic_cash,
            Color(0XFF67b667),
            Modifier.fillMaxWidth(0.5f).padding(start = 16.dp, end = 8.dp)
        )
        WalletCard(
            cardBalance,
            "Безналичные",
            R.drawable.ic_card,
            Color(0XFF2d80ff),
            Modifier.fillMaxWidth(1f).padding(end = 16.dp, start = 8.dp)
        )
    }
}

@Composable
fun WalletCard(
    cardBalance: String,
    cardName: String,
    iconId: Int,
    accentColor: Color,
    modifier: Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(accentColor.copy(alpha = 0.08f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(iconId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 6.dp, top = 8.dp, bottom = 8.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = cardName,
                        color = Color(0XFF071145),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        lineHeight = 12.sp
                    )

                    Text(
                        text = "$cardBalance BYN",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                        color = Color(0XFF1d1b20)

                    )
                    Text(
                        text = "Кошелек",
                        color = Color(0XFF7477a2),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                        lineHeight = 11.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(accentColor),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

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
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuicAction(
            "Расход",
            Color(0xFFFC665B),
            R.drawable.ic_arrow_down,
            modifier = Modifier.weight(1f)
        )
        QuicAction(
            "Доход",
            Color(0XFF50ae68),
            R.drawable.ic_plus,
            modifier = Modifier.weight(1f)
        )
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

@Composable
fun OneDayCard(day: FinanceDayUiState) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .fillMaxWidth()
    ) {
        Row() {
            Image(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0XFF686e93)),
                modifier = Modifier.padding(start = 6.dp, top = 6.dp, bottom = 6.dp)
            )
            Text(
                text = day.transactionDate,
                modifier = Modifier
                    .padding(start = 6.dp, top = 4.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 13.sp,
                color = Color(0XFF686e93),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = day.outgo,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 13.sp,
                color = Color(0XFFf20302),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = day.income,
                modifier = Modifier
                    .padding(start = 12.dp, top = 4.dp, end = 16.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 13.sp,
                color = Color(0XFF0bae31),
                fontWeight = FontWeight.SemiBold
            )
        }
        HorizontalDivider(color = Color(0XFFf8f8fb))
        Column() {
            day.transactions.forEach {
                TransactionItem(it)
                if (it != day.transactions.last()) {
                    HorizontalDivider(
                        color = Color(0XFFf8f8fb),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun TransactionItem(transaction: TransactionUiState) {
    val transactionTypeText = if (transaction.type == WalletType.CASH) "Наличные" else "Безналичные"
    val transactionTypeIcon =
        if (transaction.type == WalletType.CASH) R.drawable.ic_cash else R.drawable.ic_card

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 8.dp, start = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp)
                .background(
                    shape = CircleShape,
                    color = transaction.iconCircleColor
                )
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(transaction.iconResourceId),
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = transaction.name,
                color = Color(0XFF071145),
                modifier = Modifier.padding(top = 6.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )
            Box(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .background(
                        shape = RoundedCornerShape(6.dp),
                        color = transaction.iconCircleColor.copy(alpha = 0.12f)
                    )
            ) {
                Text(
                    text = transaction.categoryName,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = transaction.iconCircleColor,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(modifier = Modifier.padding(end = 16.dp)) {
            Text(
                text = transaction.amount,
                fontSize = 14.sp,
                color = transaction.amountColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .align(Alignment.End)
            )
            Row() {
                Text(
                    text = transactionTypeText,
                    fontSize = 10.sp,
                    color = Color(0XFF686e93),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(transactionTypeIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(16.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }

}