package com.powakaz.nesttrack.feature_profile.presentation.screen


import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.powakaz.nesttrack.feature_profile.R
import com.powakaz.nesttrack.feature_profile.presentation.components.dialogs.EditNameDialog
import com.powakaz.nesttrack.feature_profile.presentation.mapper.getDefaultAvatar
import com.powakaz.nesttrack.feature_profile.presentation.model.ProfileUiState


private val shape20 = RoundedCornerShape(20.dp)


@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreenContent(
        onEditNameClick = viewModel::showEditNameDialog,

    )
    if (uiState.isEditNameDialogVisible) {
        EditNameDialog(
            currentName = uiState.currentName,
            textNewName = uiState.editedName,
            onTextNewNameChange = viewModel::onNameChanged,
            onDismiss = viewModel::closeEditNameDialog,
            onSave = viewModel::saveName,
            onReadyToSave = uiState.isSaveEnabled
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(onEditNameClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    Text(
                        text = stringResource(id = R.string.profile_title),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ShowGeneralCard(onEditNameClick = onEditNameClick)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.your_details),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif
            )


            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                ShowDateOfBirthCard()

                Spacer(modifier = Modifier.weight(1f))

                EditAvatarCard()
            }

            Spacer(modifier = Modifier.height(20.dp))

            ShowFamilyMemberCard()

        }
    }

}

@Composable
fun ShowDateOfBirthCard() {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(220.dp)
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 8.dp,
                shape = shape20
            )
            .clip(shape20)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFD0E9),
                        Color(0xFFFDF7FB)
                    )
                )
            )
            .border(
                2.dp,
                Color.White,
                shape20
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color(0xFFFDC7E5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cake),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.date_of_birth),
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "15 марта 1995",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFEE5E99),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = {},
            modifier = Modifier
                .size(width = 120.dp, height = 32.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp
            ),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)

        ) {

            Icon(
                painter = painterResource(id = R.drawable.cal),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(12.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = stringResource(id = R.string.edit_date),
                modifier = Modifier.offset(y = ((-1).dp)),
                textAlign = TextAlign.Center,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFEE5E99),
                fontFamily = FontFamily.SansSerif,

                )

        }
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Composable
fun EditAvatarCard() {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(220.dp)
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 8.dp,
                shape = shape20
            )
            .clip(shape20)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE2D1FF),
                        Color(0xFFF5F1FF)
                    )
                )
            )
            .border(
                2.dp,
                Color.White,
                shape20
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color(0xFFD2C6FC)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ph),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.avatar),
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = {},
            modifier = Modifier
                .size(width = 120.dp, height = 32.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp
            ),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)

        ) {

            Icon(
                painter = painterResource(id = R.drawable.phh),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = stringResource(id = R.string.edit_avatar),
                modifier = Modifier.offset(y = ((-1).dp)),
                textAlign = TextAlign.Center,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6C43FF),
                fontFamily = FontFamily.SansSerif,

                )

        }
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Composable
fun ShowGeneralCard(onEditNameClick: () -> Unit) {

    Spacer(modifier = Modifier.height(10.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 8.dp,
                shape = shape20
            )
            .clip(shape20)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE7ECFF),
                        Color(0xFFF9EBFC)
                    )
                )
            )
            .border(
                3.dp,
                Color.White,
                shape20
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        ShowDefaultAvatarCard()

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Полина",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.DarkGray,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            onClick = onEditNameClick,
            modifier = Modifier.size(width = 140.dp, height = 36.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp
            ),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.pen),
                contentDescription = null,
                tint = Color(0xFF1669FF),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = stringResource(id = R.string.edit_name),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1669FF),
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Composable
fun ShowDefaultAvatarCard() {
    Box(
        modifier = Modifier
            .size(140.dp),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.girl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                /*.background(
                    brush = Brush.linearGradient(
                        ///
                    )

                )*/

        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .border(
                    3.dp,
                    Color.White,
                    CircleShape
                )

        )
    }
}

@Composable
fun ShowFamilyMemberCard() {

    Row(
        Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 8.dp,
                shape = shape20
            )
            .clip(shape20)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE5E5FC),
                        Color(0xFFF7E7FF)
                    )
                )
            )
            .border(
                width = 3.dp,
                color = Color.White,
                shape = shape20
            ),
        verticalAlignment = Alignment.CenterVertically

    ) {

        Spacer(modifier = Modifier.width(20.dp))

        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFFD2C6FC)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.calk),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(30.dp)
                    .padding(2.dp)

            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.family_member),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "c 12 февраля 2024",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF797A81),
                fontFamily = FontFamily.SansSerif
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.house),
            modifier = Modifier
                .height(100.dp)
                .offset(x = (-10).dp),
            contentDescription = null,
        )

    }

}




@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(
        onEditNameClick = { }
    )
}