package com.powakaz.nesttrack.feature_time.pres.components.dialogs.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.powakaz.nesttrack.feature_time.R
import com.powakaz.nesttrack.feature_time.pres.components.items.ActivitiesItem
import com.powakaz.nesttrack.feature_time.pres.components.dialogs.activities.lists.AvailableActivitiesColors
import com.powakaz.nesttrack.feature_time.pres.components.dialogs.activities.lists.AvailableActivitiesIcons
import com.powakaz.nesttrack.feature_time.pres.utils.mapper.findActivitiesColorToUi


@Composable
fun NewActivitiesSheet(
    onDismiss: () -> Unit,
    viewModel: NewActivitiesSheetViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    NewActivitiesSheetContent(
        onDismiss,
        uiState,
        onIconSelected = viewModel::onSelectedIcon,
        onColorSelected = viewModel::onSelectedColor,
        onNameChanged = viewModel::onNameChanged,
        onSaveActivities = {
            viewModel.onSaveActivities()
            onDismiss()
        }
        ,
        isCreateButtonEnabled = uiState.isCreateButtonEnabled
        )
}

@Composable
fun NewActivitiesSheetContent(
    onDismiss: () -> Unit,
    uiState: NewActivitiesUiState,
    onIconSelected: (Int) -> Unit,
    onColorSelected: (Color) -> Unit,
    onNameChanged: (String) -> Unit,
    onSaveActivities: () -> Unit,
    isCreateButtonEnabled: Boolean,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(horizontal = 16.dp)
    ) {


        Text(
            text = stringResource(id = R.string.new_activities),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {

            Text(
                text = stringResource(id = R.string.activities_name),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            val indicatorColor = when {
                uiState.activitiesName.isNotBlank() -> Color(0xFFA17CDE)
                uiState.hasEditedName -> Color(0xFFFF5757)
                else -> Color(0xFF888888)
            }

            OutlinedTextField(
                value = uiState.activitiesName,
                onValueChange = onNameChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                trailingIcon = {
                    if (uiState.activitiesName.isNotEmpty()) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Очистить",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(2.dp)
                                .clickable {
                                    onNameChanged("")
                                },
                            tint = Color.DarkGray

                        )
                    }
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.entered_activities_name),
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                ),
                maxLines = 1,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = indicatorColor,
                    unfocusedIndicatorColor = indicatorColor,
                    errorIndicatorColor = indicatorColor,

                    cursorColor = Color.DarkGray,
                    errorCursorColor = Color.DarkGray,

                    errorContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                isError = uiState.hasEditedName && uiState.activitiesName.isBlank(),
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(id = R.string.choose_icon_activities),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            IconsGrid(
                selectedIcon = uiState.selectedIcon,
                onIconSelected = onIconSelected
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(id = R.string.choose_color_icon_activities),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            ColorsGrid(
                selectionColor = uiState.selectedColor,
                onColorSelected = onColorSelected
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F1FC)),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Spacer(modifier = Modifier.width(10.dp))

                ActivitiesItem(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    icon = if (uiState.selectedIcon == null) {
                        painterResource(R.drawable.ic_bicycle_activities)
                    } else {
                        painterResource(uiState.selectedIcon)
                    },
                    backgroundColor = if (uiState.selectedColor == null) {
                        Color(0xFFE0D7FA)
                    } else {
                        uiState.selectedColor.findActivitiesColorToUi(alpha = 0.2f)
                    },
                    shape = CircleShape,
                    tint = uiState.selectedColor ?: Color(0xFF7B61FF),
                )

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {

                    Text(
                        text = stringResource(R.string.preview),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF9062DA),
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = uiState.activitiesName.ifBlank {
                            stringResource(R.string.activities_name)
                        },
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray,
                        fontFamily = FontFamily.SansSerif
                    )

                }

            }

            Spacer(modifier = Modifier.height(18.dp))

            TextButton(
                onClick = onSaveActivities,
                enabled = isCreateButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF835EFF))

            ) {

                Text(
                    text = stringResource(id = R.string.create),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif
                )
            }

            Spacer(modifier = Modifier.height(44.dp))
        }
    }
}

@Composable
fun ColorsGrid(
    selectionColor: Color?,
    onColorSelected: (Color) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(AvailableActivitiesColors.list) { itemColor ->

            val isSelected = itemColor == selectionColor

            Box(
                modifier = Modifier
                    .width(46.dp)
                    .height(46.dp)
                    .then(
                        if (isSelected) {
                            Modifier.border(
                                width = 2.dp,
                                color = itemColor, // тот же самый цвет
                                shape = CircleShape
                            )
                        } else {
                            Modifier
                        }
                    )
                    .padding(3.dp)
                    .background(Color.White, CircleShape)
                    .padding(3.dp)
                    .background(itemColor, CircleShape)
                    .clickable {
                        onColorSelected(itemColor)
                    }
            )
        }
    }
}

@Composable
fun IconsGrid(
    selectedIcon: Int?,
    onIconSelected: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items(AvailableActivitiesIcons.list) { itemActivities ->

            val isSelected = itemActivities == selectedIcon

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = if (isSelected) 1.dp else 0.dp,
                        color = if (isSelected) Color(0xFF7B61FF) else Color(0xFFD9D9D9),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(Color(0xFFE0D7FA))
                    .clickable(onClick = {
                        onIconSelected(itemActivities)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentDescription = null,
                    painter = painterResource(itemActivities),
                    tint = Color(0xFF7B61FF)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NewActivitiesSheetPreview() {
    NewActivitiesSheetContent(
        onDismiss = {},
        uiState = NewActivitiesUiState(),
        onIconSelected = {},
        onColorSelected = {},
        isCreateButtonEnabled = false,
        onNameChanged = {},
        onSaveActivities = {}
    )
}