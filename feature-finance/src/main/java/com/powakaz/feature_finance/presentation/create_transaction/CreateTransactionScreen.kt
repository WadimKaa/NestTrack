package com.powakaz.feature_finance.presentation.create_transaction

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.DayPosition
import com.powakaz.feature_finance.R
import com.powakaz.feature_finance.domain.model.Currency
import com.powakaz.feature_finance.domain.model.Wallet
import com.powakaz.feature_finance.domain.model.WalletType


@Composable
@Preview
fun CreateTransactionScreenRoute(viewModel: CreateTransactionViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CreateTransactionScreen(uiState, viewModel::onEvent)
}


@Composable
@Preview
fun CreateTransactionScreenPreview() {
    CreateTransactionScreen(
        CreateTransactionUiState(
            name = "Kekek"
        ),
        {}
    )
}


@Composable
fun CreateTransactionScreen(
    uiState: CreateTransactionUiState,
    onEvent: (CreateTransactionEvent) -> Unit
) {
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            CreateTransactionTopBar()
            NameTransactionCard(uiState.name, onEvent)
            Label("Кошелек")
            WalletsCard()
            Label("Сумма")
            InputSum()
            Label("Категория")
            SelectCategory(uiState.selectedCategoryIndex)
            Label("Дата")
            SelectDate()
            ButtonSaveTransaction()

        }
    }

    if (false) {
        ChoiceWalletDialog()
    }


    if (false) {
        ChoiceCategoryDialog()
    }


    if (false) {
        DataBottomSheet()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataBottomSheet() {
    ModalBottomSheet(onDismissRequest = {}, dragHandle = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(width = 30.dp, height = 4.dp)
                    .background(color = Color(0XFFcdccdb), shape = RoundedCornerShape(32.dp))
            )
        }
    }) {
        Text(
            text = "Выберите дату",
            color = Color(0XFF042154),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        HorizontalCalendar(state = rememberCalendarState(), dayContent = { day ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        if (false)
                            androidx.compose.material3.MaterialTheme.colorScheme.primary
                        else
                            androidx.compose.ui.graphics.Color.Transparent
                    )
                    .clickable(
                        enabled = day.position == DayPosition.MonthDate,
                        onClick = {}
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = day.date.dayOfMonth.toString()
                )
            }
        })
    }
}

@Composable
fun ChoiceCategoryDialog() {
    Dialog(onDismissRequest = {}) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0XFFfafafa)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 16.dp, end = 16.dp)
                )
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Text(
                        text = "Категория",
                        color = Color(0XFF042154),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Выберите категорию перевода",
                        color = Color(0XFF7a8198),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CategoryItem()
                    CategoryItem()
                    CategoryItem()
                    CategoryItem()
                    Spacer(modifier = Modifier.height(16.dp))


                }
            }
        }
    }
}

@Composable
fun CategoryItem() {
    Row(
        modifier = Modifier
            .padding(top = 4.dp)
            .background(
                color = Color(0XFFfefefe),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0XFFe5e5ec),
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_calendar),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0XFFfd4e93)),
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                .size(36.dp)
        )

        Text(
            text = "Умный дом",
            fontSize = 14.sp,
            color = Color(0XFF14274e),
            fontWeight = FontWeight.SemiBold,
            lineHeight = 16.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.ic_radiobutton_selected),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp)
        )
    }
}

@Composable
fun ChoiceWalletDialog() {
    Dialog(onDismissRequest = {}) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0XFFfafafa)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Box() {
                Image(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 16.dp, end = 16.dp)
                )
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Text(
                        text = "Откуда",
                        color = Color(0XFF042154),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Выберите кошелек",
                        color = Color(0XFF7a8198),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    WalletItem()
                    WalletItem()
                    WalletItem()
                    WalletItem()
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun WalletItem() {
    Row(
        modifier = Modifier
            .padding(top = 4.dp)
            .background(
                color = Color(0XFFfefefe),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0XFFe5e5ec),
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_cash),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                .size(42.dp)
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Наличные",
                color = Color(0XFF042154),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 12.sp
            )
            Text(
                text = "870 BYN",
                fontSize = 16.sp,
                color = Color(0XFF9599ae),
                lineHeight = 16.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.ic_radiobutton_selected),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp)
        )
    }
}


@Composable
fun Label(labelText: String) {
    Text(
        text = labelText,
        color = Color(0XFF042154),
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 16.dp, start = 16.dp)
    )
}


@Composable
fun CreateTransactionTopBar() {
    Row() {
        Image(
            painter = painterResource(R.drawable.ic_arrow_left),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0XFF5c6382)),
            modifier = Modifier
                .padding(start = 16.dp)
                .size(36.dp)
        )
        Text(
            text = "Создание транзакции",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color(0XFF14274e)
        )
    }
}

@Composable
fun NameTransactionCard(name: String, onEvent: (CreateTransactionEvent) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 12.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        )
    ) {
        Column {
            Text(
                text = "Название",
                color = Color(0XFF14274e),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Image(
                    painter = painterResource(R.drawable.ic_wallet_with_card),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(56.dp)
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { onEvent(CreateTransactionEvent.NameChange(it)) },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0XFFe2e3e7),
                        focusedTextColor = Color(0XFF6750a4)
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "Введите название", color = Color(0XFF9599ae))
                    }
                )
            }
            Text(
                text = "Например: Продукты, Кофе, Зарплата",
                modifier = Modifier.padding(start = 88.dp, top = 6.dp, bottom = 12.dp),
                fontSize = 12.sp,
                color = Color(0XFF9599ae)
            )
        }
    }
}


@Composable
fun WalletsCard() {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0XFFfefeff)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .background(color = Color(0XFFfaf9fc), shape = RoundedCornerShape(16.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 12.dp)
            ) {
                Wallet(
                    "Откуда", Wallet(1, 1, "", "", Currency.USD, WalletType.CARD, 0.4f, 1),
                    Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(start = 8.dp, end = 8.dp, bottom = 12.dp)
                        .background(
                            color = Color(0XFFa17afd),
                            shape = CircleShape
                        )

                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_from_left_to_right),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.padding(6.dp)
                    )
                }
                Wallet(
                    "Куда",
                    Wallet(1, 1, "", "", Currency.USD, WalletType.CARD, 0.4f, 1),
                    Modifier.weight(1f)
                )
            }

            Box(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 12.dp)
                    .fillMaxWidth()
                    .background(color = Color(0XFFf4effd), shape = RoundedCornerShape(8.dp))
            ) {
                //TODO подкраска текста
                Text(
                    text = "Доступно для перевода: 870 BYN",
                    color = Color(0XFF9599ae),
                    modifier = Modifier.padding(start = 18.dp, top = 12.dp, bottom = 12.dp)
                )
            }
        }
    }

}


//TODO разоабраться с адаптивной версткой
@Composable
fun Wallet(destination: String, wallet: Wallet, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = destination,
            color = Color(0XFF9599ae),
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier = Modifier
                .padding(top = 4.dp)
                .background(
                    color = Color(0XFFfefefe),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0XFFe5e5ec),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Image(
                painter = painterResource(R.drawable.ic_cash),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    .size(42.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Наличные",
                    color = Color(0XFF042154),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 12.sp
                )
                Text(
                    text = "870 BYN",
                    fontSize = 16.sp,
                    color = Color(0XFF9599ae),
                    lineHeight = 16.sp
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_arrow_schevron_down),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(18.dp)
                    .align(Alignment.CenterVertically),
                colorFilter = ColorFilter.tint(Color(0XFF626581))
            )
        }
    }
}

@Composable
fun InputSum() {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = Color(0XFFe5e5ec),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Image(
                painter = painterResource(R.drawable.ic_coins),
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            )
            BasicTextField(
                value = "150",
                onValueChange = {},
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 4.dp)
                    .width(200.dp),
                textStyle = TextStyle(
                    color = Color(0XFF042154),
                    fontSize = 42.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "BYN", fontSize = 20.sp,
                color = Color(0XFF616a84),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp)
            )
        }

        Row(modifier = Modifier.padding(top = 8.dp, start = 12.dp, end = 12.dp)) {
            SumCard("+25 BYN", Modifier.weight(1f))
            SumCard("+50 BYN", Modifier.weight(1f))
            SumCard("+100 BYN", Modifier.weight(1f))
            SumCard("+200 BYN", Modifier.weight(1f))
        }
    }
}

@Composable
fun SumCard(sum: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(start = 4.dp, end = 4.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Color(0XFFe5e5ec),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Text(
            text = sum,
            color = Color(0XFF793ffc),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 12.dp, bottom = 12.dp)

        )
    }
}


@Composable
fun SelectCategory(selectedCategoryIndex: Int) {
    Column(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
        Row(modifier = Modifier.padding(top = 8.dp)) {
            CategoryCard(modifier = Modifier.weight(1f), true)
            CategoryCard(modifier = Modifier.weight(1f), false)
            CategoryCard(modifier = Modifier.weight(1f), false)
            CategoryCard(modifier = Modifier.weight(1f), false)
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            CategoryCard(modifier = Modifier.weight(1f), false)
            CategoryCard(modifier = Modifier.weight(1f), false)
            CategoryCard(modifier = Modifier.weight(1f), false)
            CategoryCard(modifier = Modifier.weight(1f), false)
        }
    }


}

@Composable
fun CategoryCard(modifier: Modifier, isSelected: Boolean) {
    val cardContainerColor = if (isSelected) Color(0XFFf4effd) else Color(0XFFffffff)
    val borderColor = if (isSelected) Color(0XFF9670f5) else Color(0XFFe5e5ec)
    val iconColor = if (isSelected) Color(0XFF9063fd) else Color(0XFF4aa361)
    val textColor = if (isSelected) Color(0XFF834efc) else Color(0XFF616a84)

    Column(
        modifier = modifier
            .padding(start = 4.dp, end = 4.dp)
            .background(color = cardContainerColor, shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
            .height(90.dp)
    ) {
        Image(
            painterResource(R.drawable.ic_week_category),
            contentDescription = null,
            colorFilter = ColorFilter.tint(iconColor),
            modifier = Modifier
                .padding(top = 12.dp)
                .size(26.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Недельное разделение",
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 12.dp, top = 6.dp)
                .widthIn(max = 80.dp)
        )
    }
}


@Composable
fun SelectDate() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Color(0XFFe5e5ec),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Image(
            painter = painterResource(R.drawable.ic_calendar),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0XFF535c7f)),
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                .size(32.dp)
        )
        Text(
            text = "17 июля 2026",
            color = Color(0XFF14274e),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        )
        Text(
            text = "(сегодня)",
            color = Color(0XFF9599ae),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0XFF575c80)),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp)
        )

    }
}


@Composable
fun ButtonSaveTransaction() {
    Button(
        onClick = {}, colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFF6d3dfd)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Создать транзакцию")
    }
}
