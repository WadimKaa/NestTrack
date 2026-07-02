package com.powakaz.feature_auth.presentation


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.powakaz.feature_auth.R


// TODO:  Поднятие над клавиатурой


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LoginContent(uiState = state, onEvent = viewModel::onEvent)
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val uiState = LoginUiState(currentState = LoginState.TOKEN_CHECK, token = "")

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
                .background(color = Color(0XFFf7f5fc))
                .imePadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            HeadLogin(uiState)
            if (uiState.isNeedShowInputText) InputTextLogin(uiState, onEvent)
            when (uiState.currentState) {
                LoginState.TOKEN_CHECK -> LoadingCheckToken()
                LoginState.TOKEN_RIGHT -> {
                    TokenRightMark()
                    LoginButton(uiState, onEvent)
                }

                else -> LoginButton(uiState, onEvent)
            }
            //Spacer(modifier = Modifier.weight(1f))
            BottomLoginCard()
        }
    }

}


@Composable
fun HeadLogin(uiState: LoginUiState) {
    val painter =
        if (uiState.currentState == LoginState.TOKEN_RIGHT) painterResource(R.drawable.img_login_shield)
        else painterResource(R.drawable.img_login_head)

    val headText =
        if (uiState.currentState == LoginState.TOKEN_RIGHT) stringResource(R.string.login_head)
        else stringResource(R.string.login_head_not_success_state)

    val bodyText =
        if (uiState.currentState == LoginState.TOKEN_RIGHT) stringResource(R.string.login_body_token_right)
        else stringResource(R.string.login_body_token_check)




    Text(
        text = stringResource(R.string.login_app_name),
        modifier = Modifier.padding(top = 86.dp),
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0XFF05063d)
    )
    Text(
        text = stringResource(R.string.login_top_body),
        modifier = Modifier.widthIn(max = 300.dp),
        textAlign = TextAlign.Center,
        color = Color(0XFF7a7c9c),
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.sp
    )
    Image(
        painter = painter,
        contentDescription = null
    )
    Text(
        text = headText,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0XFF05063d)
    )
    Text(
        text = bodyText,
        textAlign = TextAlign.Center,
        color = Color(0XFF7a7c9c),
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.sp,
        modifier = Modifier
            .padding(top = 4.dp)
            .widthIn(max = 300.dp)
    )
}


@Composable
fun InputTextLogin(uiState: LoginUiState, onEvent: (LoginUiEvent) -> Unit) {
    val visibilityPainter =
        if (uiState.isTokenVisible) painterResource(R.drawable.ic_visibility_on) else painterResource(
            R.drawable.ic_visibility_off
        )

    val visualTransformation =
        if (uiState.isTokenVisible) VisualTransformation.None else PasswordVisualTransformation()

    val errorText = when (uiState.currentState) {
        LoginState.NETWORK_ERROR -> stringResource(R.string.login_error_internet_connection)
        LoginState.ERROR -> stringResource(R.string.login_error)
        LoginState.TOKEN_WRONG -> stringResource(R.string.login_error_token)
        else -> ""
    }

    val hintTextColor = if (uiState.isNeedShowError) Color(0XFFEB5757) else Color(0XFFc4c4d9)
    val iconColor = if (uiState.isNeedShowError) Color(0XFFEB5757) else Color(0XFFc4c4d9)




    Column(modifier = Modifier.fillMaxWidth()) {
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
                isError = uiState.isNeedShowError,
                placeholder = {
                    Text(
                        text = stringResource(R.string.login_input_token_label),
                        color = hintTextColor,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFf9f8fd),
                    errorContainerColor = Color(0xFFf9f8fd),
                    unfocusedContainerColor = Color(0xFFf9f8fd),
                    focusedBorderColor = Color(0xFFFFFFFF),
                    unfocusedBorderColor = Color(0xFFFFFFFF),
                    errorBorderColor = Color(0xFFE53935),
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_lock),
                        contentDescription = null,
                        tint = iconColor
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = visibilityPainter,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.clickable(
                            onClick = { onEvent(LoginUiEvent.ChangeTokenVisibility) },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                    )
                },
                visualTransformation = visualTransformation
            )
        }
        if (uiState.isNeedShowError) {
            Row(modifier = Modifier.padding(top = 10.dp, start = 24.dp)) {
                if (uiState.isNeedShowErrorGear) {
                    Image(
                        painter = painterResource(R.drawable.ic_gear),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Text(
                    text = errorText,
                    color = Color(0XFFEB5757),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }
    }
}


@Composable
fun LoginButton(uiState: LoginUiState, onEvent: (LoginUiEvent) -> Unit) {
    val buttonText =
        if (uiState.currentState == LoginState.NORMAL) stringResource(R.string.login_button_text) else stringResource(
            R.string.login_button_text_continue
        )

    Button(
        onClick = {
            if (uiState.currentState == LoginState.NORMAL) onEvent(LoginUiEvent.ClickLoginButon) else onEvent(
                LoginUiEvent.ClickContinueButton
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFF7b4af7),
            disabledContainerColor = Color(0XFFaea6f8),
            contentColor = Color(0XFFFFFFFF),
            disabledContentColor = Color(0XFFFFFFFF)
        ),
        enabled = uiState.isButtonLoginEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = buttonText, fontWeight = FontWeight.SemiBold
        )
    }
}


@Composable
fun LoadingCheckToken() {
    Row(modifier = Modifier.padding(top = 24.dp)) {
        CircularProgressIndicator(
            color = Color(0XFF7769c2),
            trackColor = Color(0XFFebe8f9),
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = stringResource(R.string.login_label_check_token),
            color = Color(0XFF7a7c9c),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Composable
fun TokenRightMark() {
    Row(modifier = Modifier.padding(top = 24.dp)) {
        Image(painter = painterResource(R.drawable.ic_check), contentDescription = null)
        Text(
            text = stringResource(R.string.login_label_token_right),
            color = Color(0XFF50B36C),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}


@Composable
fun BottomLoginCard() {
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
                    text = stringResource(R.string.login_label_info_security), fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Text(
                    text = stringResource(R.string.login_label_info_security_text),
                    fontWeight = FontWeight.Normal,
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

