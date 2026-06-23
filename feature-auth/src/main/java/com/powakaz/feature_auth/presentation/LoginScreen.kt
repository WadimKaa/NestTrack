package com.powakaz.feature_auth.presentation


import com.powakaz.feature_auth.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val uiState = LoginUiState()

    LoginScreen(
        uiState = uiState,
        onEvent = {}
    )

}


@Composable
fun LoginScreen(uiState: LoginUiState, onEvent: (LoginUiEvent) -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Head()
        }
    }

}

private val AuthFontFamily = FontFamily(
    Font(
        resId = R.font.nunito_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.nunito_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.nunito_semibold,
        weight = FontWeight.SemiBold
    )
)

@Composable
fun Head() {
    Text(
        text = "Nest Tracker",
        modifier = Modifier.padding(top = 86.dp),
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = AuthFontFamily,
        color = Color(0XFF05063d)
    )
    Text(
        text = "Для учета времени и семейных финансов",
        modifier = Modifier.widthIn(max = 250.dp),
        textAlign = TextAlign.Center,
        color = Color(0XFF7a7c9c),
        fontWeight = FontWeight.SemiBold
    )
    Image(painter = painterResource(R.drawable.img_login_head), contentDescription = null)
    Text(text = "Вход по токену", fontSize = 32.sp)
    Text(text = "Введите ваш персональный токен для доступа к приложению")
}

