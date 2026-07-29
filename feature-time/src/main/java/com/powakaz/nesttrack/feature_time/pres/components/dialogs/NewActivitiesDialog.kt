package com.powakaz.nesttrack.feature_time.pres.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import androidx.compose.ui.window.Dialog
import com.powakaz.nesttrack.feature_time.R
import com.powakaz.nesttrack.feature_time.pres.components.ActivitiesItem
import com.powakaz.nesttrack.feature_time.pres.utils.mapper.findActivitiesIconToUi


@Composable
fun NewActivitiesDialog(
    onDismiss: () -> Unit,
) {
    NewActivitiesDialogContent(onDismiss)
}

@Composable
fun NewActivitiesDialogContent(
    onDismiss: () -> Unit,
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
                    unfocusedContainerColor = Color.White
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

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(id = R.string.choose_icon_activities),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))



            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                val item =
                    listOf("one", "two", "one", "two", "one", "two", "one", "two", "two", "one")

                items(item) { itemActivities ->
                    ActivitiesItem(
                        modifier = Modifier
                            .width(55.dp)
                            .height(55.dp),
                        icon = painterResource(R.drawable.ic_walk_activities1),  ///!!!!
                        backgroundColor = Color(0xFFE9DDFC),
                        shape = RoundedCornerShape(12.dp),
                        tint = Color(0xFFA17CDE),
                        isSelected = false, ////!!!!!
                        onClick = {
                            ///// !!!!
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(id = R.string.choose_color_icon_activities),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))


            LazyVerticalGrid(
                columns = GridCells.Fixed(6),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                val item =
                    listOf("one", "two", "one", "two", "one", "two", "one", "two", "two", "one", "two", "one", )

                items(item) { itemColor ->
                    Box(
                        modifier = Modifier
                            .width(46.dp)
                            .height(46.dp)
                            .border(
                                1.dp,
                                Color(0xFF5CCB5C),
                                CircleShape
                            )
                            .padding(3.dp)
                            .background(Color.White, CircleShape)
                            .padding(3.dp)
                            .background(Color(0xFF42B92F), CircleShape)
                            .clickable { }
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(Color(0xFFFAF9FD))
                    .clip(RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Spacer(modifier = Modifier.width(10.dp))

                ActivitiesItem(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    icon = painterResource(id = R.drawable.ic_walk_activities1), ///!!
                    backgroundColor = Color(0xFFE9DDFC), ///!!
                    shape = CircleShape,
                    tint = Color(0xFF9062DA), ///!!
                )

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {

                    Text(
                        text = stringResource(R.string.preview),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF9062DA),
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Прогулка",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        fontFamily = FontFamily.SansSerif
                    )

                }

            }

            Spacer(modifier = Modifier.height(18.dp))


           // Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = {},
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


@Preview(showBackground = true)
@Composable
fun NewActivitiesDialogPreview() {
    NewActivitiesDialogContent(
        onDismiss = {}
    )
}