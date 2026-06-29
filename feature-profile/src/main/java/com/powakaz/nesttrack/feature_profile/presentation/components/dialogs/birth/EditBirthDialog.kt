package com.powakaz.nesttrack.feature_profile.presentation.components.dialogs.birth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.powakaz.nesttrack.feature_profile.R
import com.powakaz.nesttrack.feature_profile.presentation.components.buttons.CancelButton
import com.powakaz.nesttrack.feature_profile.presentation.components.buttons.SaveButton
import com.powakaz.nesttrack.feature_profile.presentation.utils.formatDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun EditBirthDialog(
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    onChooseDateClick: () -> Unit,
    currentDate: Long?,
    selectedDate: Long?,
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        EditBirthDialogContent(
            onDismiss = onDismiss, onChooseDateClick, onSave, currentDate,
            selectedDate)
    }
}


@Composable
fun EditBirthDialogContent(
    onDismiss: () -> Unit, onChooseDateClick: () -> Unit, onSave: () -> Unit, currentDate: Long?,
    selectedDate: Long?
) {

    val currentDateText = currentDate?.let(::formatDate).orEmpty()
    val editedDateText = selectedDate?.let { formatDate(it) }
        ?: stringResource(id = R.string.choose_new_date)

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.change_date_of_birth),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.current_date),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF1E9E9)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = currentDateText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Start,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.new_date),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))


            TextButton(
                onClick = onChooseDateClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 2.dp,
                        color = Color(0xFFA17CDE),
                        shape = RoundedCornerShape(12.dp)
                    ),
                shape = RoundedCornerShape(12.dp)

            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = editedDateText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA17CDE),
                        fontFamily = FontFamily.SansSerif
                    )

                    Image(
                        painter = painterResource(R.drawable.calk),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CancelButton(
                    text = "Отмена",
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.weight(1f))

                SaveButton(
                    text = "Сохранить",
                    onClick = onSave,
                    onEnabled = true,
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditBirthDialogPreview() {
    EditBirthDialogContent(onDismiss = {},
        onChooseDateClick = {},
        onSave = {},
        currentDate = 123456L,
        selectedDate = 123456L)
}