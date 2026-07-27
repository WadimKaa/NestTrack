package com.powakaz.nesttrack.feature_time.pres.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.powakaz.nesttrack.feature_time.R
import com.powakaz.nesttrack.feature_time.pres.components.ActivitiesItem
import com.powakaz.nesttrack.feature_time.pres.utils.mapper.findActivitiesIconToUi


@Composable
fun NewActivitiesDialog(
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        NewActivitiesDialogContent(onDismiss)
    }

}

@Composable
fun NewActivitiesDialogContent(
    onDismiss: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
                .padding(16.dp)
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

            Text(
                text = stringResource(id = R.string.activities_name),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Очистить",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(2.dp)
                                .clickable {
                                },
                            tint = Color.DarkGray

                        )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                maxLines = 1,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFFA17CDE),
                    unfocusedIndicatorColor = Color(0xFF888888),
                    errorIndicatorColor = Color(0xFFFF5757),

                    cursorColor = Color.DarkGray,
                    errorCursorColor = Color.DarkGray,

                    errorContainerColor = Color.Unspecified,
                    focusedContainerColor = Color.Unspecified,
                    unfocusedContainerColor = Color.Unspecified
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.entered_activities_name),
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.choose_icon_activities),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val item = listOf("one", "two", "one", "two", "one", "two", "one", "two")

                items(item) { item ->
                    ActivitiesItem(
                        modifier = Modifier
                            .width(60.dp)
                            .height(55.dp),
                        icon = painterResource(R.drawable.bus),  ///!!!!
                        backgroundColor = Color(0xFFE9DDFC),
                        shape = RoundedCornerShape(16.dp),
                        tint = Color(0xFFA17CDE),
                        isSelected = true, ////!!!!!
                        onClick = {
                            ///// !!!!
                        }

                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun NewActivitiesDialogPreview() {
    NewActivitiesDialogContent(
        onDismiss = {}
    )
}