package com.powakaz.feature_auth.presentation


import androidx.compose.foundation.BorderStroke
import com.powakaz.feature_auth.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LoginContent(uiState = state, onEvent = viewModel::onEvent)

}


@Preview(showBackground = true, device = "id:pixel_7")
@Composable
fun LoginScreenPreview() {
    val uiState = LoginUiState()

    LoginContent(
        uiState = uiState,
        onEvent = {}
    )

}


@Composable
fun LoginContent(uiState: LoginUiState, onEvent: (LoginUiEvent) -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = Color(0XFFf7f5fc)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeadLogin()
            InputTextLogin(uiState, onEvent)
            LoginButton()
            Spacer(modifier = Modifier.weight(1f))
            BottomLoginCard()
        }
    }

}

@Composable
fun HeadLogin() {
    val isPreview = androidx.compose.ui.platform.LocalInspectionMode.current


    val AuthFontFamily = if (isPreview) {
        FontFamily.Default
    } else {
        FontFamily(
            Font(R.font.nunito_regular, FontWeight.Normal),
            Font(R.font.nunito_bold, FontWeight.Bold),
            Font(R.font.nunito_semibold, FontWeight.SemiBold)
        )
    }


    Text(
        text = "Nest Tracker",
        modifier = Modifier.padding(top = 86.dp),
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold,
        //fontFamily = AuthFontFamily,
        color = Color(0XFF05063d)
    )
    Text(
        text = "Для учета времени и семейных финансов",
        modifier = Modifier.widthIn(max = 300.dp),
        textAlign = TextAlign.Center,
        color = Color(0XFF7a7c9c),
        //fontFamily = AuthFontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.sp
    )
    Image(
        painter = painterResource(R.drawable.img_login_head),
        contentDescription = null
    )
    Text(
        text = "Вход по токену",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        //fontFamily = AuthFontFamily,
        color = Color(0XFF05063d)
    )
    Text(
        text = "Введите ваш персональный токен для доступа к приложению",
        textAlign = TextAlign.Center,
        color = Color(0XFF7a7c9c),
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.sp,
        //fontFamily = AuthFontFamily,
        modifier = Modifier
            .padding(top = 4.dp)
            .widthIn(max = 300.dp)
    )
}


@Composable
fun InputTextLogin(uiState: LoginUiState, onEvent: (LoginUiEvent) -> Unit) {
    val isPreview = androidx.compose.ui.platform.LocalInspectionMode.current


    val AuthFontFamily = if (isPreview) {
        FontFamily.Default
    } else {
        FontFamily(
            Font(R.font.nunito_regular, FontWeight.Normal),
            Font(R.font.nunito_bold, FontWeight.Bold),
            Font(R.font.nunito_semibold, FontWeight.SemiBold)
        )
    }

    val visibilityPainter =
        if (uiState.isTokenVisible) painterResource(R.drawable.ic_visibility_on) else painterResource(
            R.drawable.ic_visibility_off
        )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.5.dp
        ),
        shape = RoundedCornerShape(size = 20.dp)
    ) {
        OutlinedTextField(
            value = uiState.token,
            onValueChange = { onEvent(LoginUiEvent.TokenChanged(it)) },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Введите токен",
                    color = Color(0XFF7a7c9c),
                    //fontFamily = AuthFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            },
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFf9f8fd),
                unfocusedContainerColor = Color(0xFFf9f8fd),
                focusedBorderColor = Color(0xFFFFFFFF),
                unfocusedBorderColor = Color(0xFFFFFFFF),
                errorBorderColor = Color(0xFFE53935),
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = null,
                    tint = Color(0XFFc4c4d9)
                )
            },
            trailingIcon = {
                Icon(
                    painter = visibilityPainter,
                    contentDescription = null,
                    tint = Color(0XFFc4c4d9),
                    modifier = Modifier.clickable(
                        onClick = { onEvent(LoginUiEvent.ChangeTokenVisibility) },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                )
            }
        )
    }

}


@Composable
fun LoginButton() {
    val isPreview = androidx.compose.ui.platform.LocalInspectionMode.current

    val AuthFontFamily = if (isPreview) {
        FontFamily.Default
    } else {
        FontFamily(
            Font(R.font.nunito_regular, FontWeight.Normal),
            Font(R.font.nunito_bold, FontWeight.Bold),
            Font(R.font.nunito_semibold, FontWeight.SemiBold)
        )
    }

    Button(
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFF7b4af7),
            disabledContainerColor = Color(0XFFaea6f8)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = "Войти", fontWeight = FontWeight.SemiBold,
            fontFamily = AuthFontFamily,
        )
    }
}


@Composable
fun BottomLoginCard() {
    val isPreview = androidx.compose.ui.platform.LocalInspectionMode.current

    val AuthFontFamily = if (isPreview) {
        FontFamily.Default
    } else {
        FontFamily(
            Font(R.font.nunito_regular, FontWeight.Normal),
            Font(R.font.nunito_bold, FontWeight.Bold),
            Font(R.font.nunito_semibold, FontWeight.SemiBold)
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 24.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0XFFf9f8fd)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.5.dp
        ),
        border = BorderStroke(width = 1.dp, color = Color(0XFFf9f8fd))
    ) {
        Row() {
            Image(
                painter = painterResource(R.drawable.img_shield),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 64.dp, width = 64.dp)
                    .padding(start = 8.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Ваши данные защищены", fontWeight = FontWeight.SemiBold,
                    //fontFamily = AuthFontFamily,
                    fontSize = 12.sp
                )
                Text(
                    text = "Доступ к приложению возможен только по вашему уникальному токену",
                    fontWeight = FontWeight.Normal,
                    //fontFamily = AuthFontFamily,
                    color = Color(0XFF7a7c9c),
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    modifier = Modifier
                        .sizeIn(maxWidth = 250.dp)
                )
            }
        }
    }
}

