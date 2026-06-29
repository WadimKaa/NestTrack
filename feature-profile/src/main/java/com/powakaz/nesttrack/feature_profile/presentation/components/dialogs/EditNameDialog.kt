package com.powakaz.nesttrack.feature_profile.presentation.components.dialogs


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
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
import com.powakaz.nesttrack.feature_profile.R
import com.powakaz.nesttrack.feature_profile.presentation.components.buttons.CancelButton
import com.powakaz.nesttrack.feature_profile.presentation.components.buttons.SaveButton



@Composable
fun EditNameDialog(
    currentName: String,
    textNewName: String,
    onTextNewNameChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    onReadyToSave: Boolean

) {

    Dialog(
        onDismissRequest = onDismiss
    ) {
        EditNameDialogContent(
            currentName = currentName,
            textNewName = textNewName,
            onTextNewNameChange = onTextNewNameChange,
            onDismiss = onDismiss,
            onSave = onSave,
            onReadyToSave = onReadyToSave
        )
    }
}

@Composable
fun EditNameDialogContent(
    currentName: String,
    textNewName: String,
    onTextNewNameChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    onReadyToSave: Boolean
) {

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
                text = stringResource(id = R.string.edit_name),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.current_name),
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                    text = currentName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Start,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.new_name),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            var isFocused by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = textNewName,
                onValueChange = onTextNewNameChange,
                trailingIcon = {
                    if (textNewName.isNotEmpty()) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Очистить",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(2.dp)
                                .clickable {
                                    onTextNewNameChange("")
                                }

                        )
                    }
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.entered_name),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },

                isError = !onReadyToSave && isFocused,

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

                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CancelButton(
                    text = "Отмена",
                    onClick = onDismiss,
                    modifier = Modifier.width(140.dp).height(50.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                SaveButton(
                    text = "Сохранить",
                    onClick = onSave,
                    onEnabled = onReadyToSave,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun EditNameDialogPreview() {
    EditNameDialogContent(
        currentName = "",
        textNewName = "",
        onTextNewNameChange = {},
        onDismiss = {},
        onSave = {},
        onReadyToSave = false
    )
}