package com.powakaz.feature_finance.presentation.create_transaction

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.powakaz.feature_finance.R
import com.powakaz.feature_finance.presentation.create_transaction.model.CategoryUi
import com.powakaz.feature_finance.presentation.create_transaction.model.CreateTransactionUiState
import com.powakaz.feature_finance.presentation.create_transaction.model.ScreenState
import com.powakaz.feature_finance.presentation.create_transaction.model.WalletDialogTarget
import com.powakaz.feature_finance.presentation.create_transaction.model.WalletInitType
import com.powakaz.feature_finance.presentation.create_transaction.model.WalletUi
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
@Preview
fun CreateTransactionScreenRoute(viewModel: CreateTransactionViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            if (event is UiEvent.ShowErrorToast) {
                Log.e("LOL", uiState.error.toString())

                val errorText = when (uiState.error) {
                    ScreenState.SHORT_NAME_ERROR -> "Слишком короткое название"
                    ScreenState.SAME_WALLET_ERROR -> "Выбраны одинаковые кошельки"
                    ScreenState.NOT_ENOUGH_MONEY_ERROR -> "Недостаточно денег"
                    ScreenState.NULL_TRANSACTION_ERROR -> "Введите сумму"
                }

                Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
            }
        }
    }

    CreateTransactionScreen(uiState, viewModel::onEvent)
}


@Composable
@Preview
fun CreateTransactionScreenPreview() {
    CreateTransactionScreen(
        CreateTransactionUiState(
            name = "Kekek",
            isDateDialogVisible = true,
            categories = listOf(
                CategoryUi(
                    -1, null, "Рефенансирование", R.drawable.ic_calendar, Color(
                        0xFFFF4747
                    )
                ),
                CategoryUi(
                    -1, null, "Рефенансирование", R.drawable.ic_calendar, Color(
                        0xFFFF4747
                    )
                ),
                CategoryUi(
                    -1, null, "Рефенансирование", R.drawable.ic_calendar, Color(
                        0xFFFF4747
                    )
                ),
                CategoryUi(
                    -1, null, "Рефенансирование", R.drawable.ic_calendar, Color(
                        0xFFFF4747
                    )
                )

            )
        ),
        {}
    )
}


@Composable
fun CreateTransactionScreen(
    uiState: CreateTransactionUiState,
    onEvent: (CreateTransactionEvent) -> Unit
) {

    val categoryRow = uiState.categories.chunked(4)

    Scaffold() { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            item {
                CreateTransactionTopBar()
            }
            item {
                NameTransactionCard(uiState, onEvent)
            }
            item {
                Label("Кошелек")
            }
            item {
                WalletsCard(uiState, onEvent)
            }
            item {
                Label("Сумма")
            }
            item {
                InputSum(uiState.amount, onEvent)
            }
            item {
                Label("Категория")
            }
            items(items = categoryRow, key = { it.first().id }) { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    row.forEach { category ->
                        CategoryCard(
                            category,
                            Modifier.weight(1f),
                            uiState.selectedCategoryIndex == category.id,
                            onEvent
                        )
                    }


                    repeat(4 - row.size) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
            item {
                Label("Дата")
            }
            item {
                SelectDate(uiState, onEvent)
            }
            item {
                ButtonSaveTransaction(uiState, onEvent)
            }
        }
    }

    if (uiState.isWalletDialogVisible) {
        ChoiceWalletDialog(uiState, onEvent)
    }


    if (false) {
        ChoiceCategoryDialog()
    }


    if (uiState.isDateDialogVisible) {
        DateBottomSheet(uiState, onEvent)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet(uiState: CreateTransactionUiState, onEvent: (CreateTransactionEvent) -> Unit) {
    val month = YearMonth.from(uiState.tempSelectedDate)
    val state = rememberCalendarState(
        startMonth = month.minusMonths(100),
        endMonth = month.plusMonths(100),
        firstVisibleMonth = month,
        firstDayOfWeek = firstDayOfWeekFromLocale()
    )

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
                onEvent(CreateTransactionEvent.CloseDateDialog)
            }
        },
        dragHandle = {
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
        Column {
            Text(
                text = "Выберите дату",
                color = Color(0XFF042154),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )


            MonthHeader(state.firstVisibleMonth.yearMonth, state)

            HorizontalCalendar(
                state = state,
                dayContent = { day ->
                    DayCell(day, uiState.tempSelectedDate, onEvent)
                },
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .height((6 * 58).dp)
            )

            QuickDateSelect(uiState, onEvent)

            Button(
                onClick = {
                    scope.launch {
                        onEvent(CreateTransactionEvent.SaveDate)
                        sheetState.hide()
                        onEvent(CreateTransactionEvent.CloseDateDialog)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0XFF6f46f6)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Выбрать дату", color = Color.White, fontWeight = FontWeight.SemiBold)
            }

            Text(
                text = "Отмена",
                color = Color(0XFF7047f8),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp, bottom = 16.dp)
                    .clickable(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                                onEvent(CreateTransactionEvent.CloseDateDialog)
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() })
            )

        }
    }
}

@Composable
fun QuickDateSelect(uiState: CreateTransactionUiState, onEvent: (CreateTransactionEvent) -> Unit) {
    Text(
        text = "Быстрый выбор",
        color = Color(0XFF575e7d),
        modifier = Modifier.padding(start = 16.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(3) { index ->
            QuickDateSelectItem(
                Modifier
                    .weight(1f), index, uiState, onEvent
            )
        }
    }
}

@Composable
fun QuickDateSelectItem(
    modifier: Modifier,
    index: Int,
    uiState: CreateTransactionUiState,
    onEvent: (CreateTransactionEvent) -> Unit
) {
    val isSelected = uiState.quickDateActions[index].localDate == uiState.tempSelectedDate
    val borderColor = if (isSelected) Color(0XFF6e45f6) else Color(0XFFe5e5ec)
    val textColor = if (isSelected) Color(0XFF6e45f6) else Color(0XFF606487)
    val containerColor = if (isSelected) Color(0XFFf2eefc) else Color(0XFFfdfdfd)

    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = containerColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = { onEvent(CreateTransactionEvent.SelectTempDate(uiState.quickDateActions[index].localDate)) })

    ) {
        Row(modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 16.dp, bottom = 16.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0XFF754ffe)),
                modifier = Modifier.align(
                    Alignment.CenterVertically
                )
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = uiState.quickDateActions[index].label,
                    color = textColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    lineHeight = 12.sp
                )
                Text(
                    text = uiState.quickDateActions[index].readableDate,
                    color = textColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    lineHeight = 12.sp
                )
            }
        }
    }
}


val calendarHeadFormatter = DateTimeFormatter.ofPattern("LLLL yyyy", Locale("ru"))
val clickerDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale("ru"))
val dayOfWeekFormatter = DateTimeFormatter.ofPattern("E", Locale("ru"))

@Composable
fun MonthHeader(month: YearMonth, state: CalendarState) {
    val daysOfWeek = remember { daysOfWeek() }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Box(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0XFF784ff1)),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable(
                        onClick = {
                            scope.launch {
                                state.animateScrollToMonth(month.minusMonths(1))
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() })
            )
            Text(
                text = month.format(calendarHeadFormatter)
                    .replaceFirstChar { it.uppercase() },
                modifier = Modifier.align(Alignment.Center),
                color = Color(0XFF042154),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
            Image(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0XFF784ff1)),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(
                        onClick = {
                            scope.launch {
                                state.animateScrollToMonth(month.plusMonths(1))
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() })
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            daysOfWeek.forEach { day ->
                Text(
                    text = dayOfWeekFormatter.format(day).replaceFirstChar { it.uppercase() },
                    modifier = Modifier.weight(1f),
                    color = Color(0XFF5d6484),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }

        }
    }
}

enum class DayType { NORMAL, WEEKEND, OUT_MONTH, SELECTED }

fun getDayType(day: CalendarDay, selectedDate: LocalDate): DayType {
    return when {
        day.position != DayPosition.MonthDate -> {
            DayType.OUT_MONTH
        }

        day.date == selectedDate -> {
            DayType.SELECTED
        }

        day.date.dayOfWeek == DayOfWeek.SATURDAY || day.date.dayOfWeek == DayOfWeek.SUNDAY -> {
            DayType.WEEKEND
        }

        else -> {
            DayType.NORMAL
        }
    }
}

@Composable
fun DayCell(day: CalendarDay, selectedDate: LocalDate, onEvent: (CreateTransactionEvent) -> Unit) {
    val dateType = getDayType(day, selectedDate)

    var textColor = when (dateType) {
        DayType.NORMAL -> Color(0XFF040508)
        DayType.WEEKEND -> Color(0XFF643df6)
        DayType.OUT_MONTH -> Color(0XFF9c9ca6)
        DayType.SELECTED -> Color(0xFFFFFFFF)
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(
                onClick = {
                    if (dateType == DayType.NORMAL || dateType == DayType.WEEKEND) onEvent(
                        CreateTransactionEvent.SelectTempDate(
                            day.date
                        )
                    )
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }),
        contentAlignment = Alignment.Center
    ) {
        if (dateType == DayType.SELECTED) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()
                    .background(color = Color(0XFF7149f8), shape = CircleShape)
            )
        }
        Text(text = day.date.dayOfMonth.toString(), color = textColor)
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
fun ChoiceWalletDialog(
    uiState: CreateTransactionUiState,
    onEvent: (CreateTransactionEvent) -> Unit
) {
    val destinationType =
        if (uiState.walletDialogTarget == WalletDialogTarget.FROM) "Откуда" else "Куда"
    val currentWalletId =
        if (uiState.walletDialogTarget == WalletDialogTarget.FROM) uiState.fromWallet.id else uiState.toWallet.id

    Dialog(onDismissRequest = { onEvent(CreateTransactionEvent.CloseWalletPicker) }) {
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
                        .clickable(onClick = { onEvent(CreateTransactionEvent.CloseWalletPicker) })
                )
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Text(
                        text = destinationType,
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
                    LazyColumn {
                        item {
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                        items(items = uiState.wallets, key = { it.id ?: -1 }) {
                            WalletItem(it, it.id == currentWalletId, onEvent)
                        }
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WalletItem(wallet: WalletUi, isSelected: Boolean, onEvent: (CreateTransactionEvent) -> Unit) {
    val radioButtonImageId =
        if (isSelected) R.drawable.ic_radiobutton_selected else R.drawable.ic_radiobutton_unselected
    val borderColor = if (isSelected) Color(0XFF7d4efc) else Color(0XFFe5e5ec)
    val containerColor = if (isSelected) Color(0XFFf7f4fc) else Color(0XFFfefefe)

    Row(
        modifier = Modifier
            .padding(top = 4.dp)
            .background(
                color = containerColor,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .clickable(onClick = { onEvent(CreateTransactionEvent.SelectWallet(wallet.id)) })
    ) {
        Image(
            painter = painterResource(wallet.iconId),
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
                text = wallet.name,
                color = Color(0XFF042154),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 12.sp
            )
            Text(
                text = wallet.balanceLabel,
                fontSize = 16.sp,
                color = Color(0XFF9599ae),
                lineHeight = 16.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(radioButtonImageId),
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
fun NameTransactionCard(
    uiState: CreateTransactionUiState,
    onEvent: (CreateTransactionEvent) -> Unit
) {

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
                    value = uiState.name,
                    onValueChange = { onEvent(CreateTransactionEvent.NameChange(it)) },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0XFFe2e3e7),
                        focusedBorderColor = Color(0xFF9682C7),
                        focusedTextColor = Color(0XFF042154),
                        unfocusedTextColor = Color(0XFF616a84)
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "Введите название", color = Color(0XFF9599ae))
                    },
                    trailingIcon = {
                        if (uiState.letterCount > 0) {
                            IconButton(onClick = { onEvent(CreateTransactionEvent.NameChange("")) }) {
                                Image(
                                    painter = painterResource(R.drawable.ic_close),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )
            }
            Text(
                text = "${uiState.letterCount}/${uiState.MAX_NAME_LETTER_COUNT}",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0XFF9599ae),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 18.dp, bottom = 20.dp, top = 2.dp)
            )
        }
    }
}


@Composable
fun WalletsCard(uiState: CreateTransactionUiState, onEvent: (CreateTransactionEvent) -> Unit) {
    val availableMoneyText =
        if (uiState.fromWallet.initType != WalletInitType.INIT) "${uiState.fromWallet.balanceLabel} BYN" else "0 BYN"
    val availableMoneyTextColor =
        if (uiState.fromWallet.balance == 0f) Color(0xFFFD7AB3) else Color(0XFFa17afd)

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
                WalletCard(
                    "Откуда", uiState.fromWallet, Modifier.weight(1f), onEvent
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
                WalletCard(
                    "Куда",
                    uiState.toWallet,
                    Modifier.weight(1f),
                    onEvent
                )
            }

            Row(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 12.dp)
                    .fillMaxWidth()
                    .background(color = Color(0XFFf4effd), shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Доступно для перевода:",
                    color = Color(0XFF9599ae),
                    modifier = Modifier.padding(start = 18.dp, top = 12.dp, bottom = 12.dp)
                )
                Text(
                    text = availableMoneyText,
                    color = availableMoneyTextColor,
                    modifier = Modifier.padding(start = 2.dp, top = 12.dp, bottom = 12.dp)
                )
            }
        }
    }

}


@Composable
fun WalletCard(
    destination: String,
    wallet: WalletUi,
    modifier: Modifier,
    onEvent: (CreateTransactionEvent) -> Unit
) {
    val icon = if (wallet.initType != WalletInitType.INIT) wallet.iconId else R.drawable.ic_cash
    val balance = if (wallet.initType != WalletInitType.INIT) wallet.balanceLabel else "..."
    val walletName = if (wallet.initType != WalletInitType.INIT) wallet.name else "Загрузка"

    val type = if (destination == "Откуда") WalletDialogTarget.FROM else WalletDialogTarget.TO

    Column(modifier = modifier) {
        Text(
            text = destination,
            color = Color(0XFF9599ae),
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
                .clickable(onClick = { onEvent(CreateTransactionEvent.OpenWalletPicker(type)) })
        ) {
            Image(
                painter = painterResource(icon),
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
                    text = walletName,
                    color = Color(0XFF042154),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 10.sp,
                    modifier = Modifier.widthIn(max = 60.dp)
                )
                Text(
                    text = balance,
                    fontSize = 14.sp,
                    color = Color(0XFF9599ae),
                    lineHeight = 16.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
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
fun InputSum(amount: Int, onEvent: (CreateTransactionEvent) -> Unit) {
    val amountText = if (amount == 0) "" else amount.toString()

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
                value = amountText,
                onValueChange = {
                    onEvent(CreateTransactionEvent.AmountChange(if (it.isNotEmpty()) it.toInt() else 0))
                },
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
            SumCard(25, Modifier.weight(1f), onEvent)
            SumCard(50, Modifier.weight(1f), onEvent)
            SumCard(100, Modifier.weight(1f), onEvent)
            SumCard(200, Modifier.weight(1f), onEvent)
        }
    }
}

@Composable
fun SumCard(sum: Int, modifier: Modifier, onEvent: (CreateTransactionEvent) -> Unit) {
    val shape = RoundedCornerShape(12.dp)

    Box(
        modifier = modifier
            .padding(start = 4.dp, end = 4.dp)
            .clip(shape)
            .background(color = Color.White)
            .border(
                width = 1.dp,
                color = Color(0XFFe5e5ec),
                shape = shape
            )
            .clickable(onClick = { onEvent(CreateTransactionEvent.IncreaseAmount(sum)) })
    ) {
        Text(
            text = "+$sum BYN",
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
fun CategoryCard(
    category: CategoryUi,
    modifier: Modifier,
    isSelected: Boolean,
    onEvent: (CreateTransactionEvent) -> Unit
) {
    val cardContainerColor = if (isSelected) Color(0XFFf4effd) else Color(0XFFffffff)
    val borderColor = if (isSelected) Color(0XFF9670f5) else Color(0XFFe5e5ec)
    val iconColor = if (isSelected) Color(0XFF9063fd) else category.iconColor
    val textColor = if (isSelected) Color(0XFF834efc) else Color(0XFF616a84)

    Column(
        modifier = modifier
            .background(color = cardContainerColor, shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
            .height(80.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onEvent(CreateTransactionEvent.SelectCategory(category.id))
                })
    ) {
        Image(
            painterResource(category.iconResourceId),
            contentDescription = null,
            colorFilter = ColorFilter.tint(iconColor),
            modifier = Modifier
                .padding(top = 12.dp)
                .size(30.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = category.name.uppercase(),
            fontSize = 8.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 12.dp, top = 6.dp, start = 8.dp, end = 8.dp)
                .widthIn(max = 80.dp)
        )
    }
}


@Composable
fun SelectDate(uiState: CreateTransactionUiState, onEvent: (CreateTransactionEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .border(
                width = 1.dp,
                color = Color(0XFFe5e5ec),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = { onEvent(CreateTransactionEvent.OpenDateDialog) })
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
            text = clickerDateFormatter.format(uiState.selectedDate),
            color = Color(0XFF14274e),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        )
        Text(
            text = uiState.clickDateLabel,
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
fun ButtonSaveTransaction(
    uiState: CreateTransactionUiState,
    onEvent: (CreateTransactionEvent) -> Unit
) {
    val containerColor = if (uiState.isCanSave) Color(0XFF6d3dfd) else Color(0XFFe8e8ec)
    val textColor = if (uiState.isCanSave) Color(0xFFFFFFFF) else Color(0XFF959ab3)

    Button(
        onClick = { onEvent(CreateTransactionEvent.ClickSaveButton) },
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Создать транзакцию", color = textColor)
    }
}
