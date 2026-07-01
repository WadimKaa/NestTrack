package com.powakaz.nesttrack.feature_profile.presentation.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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

@Composable
fun EditAvatarDialog(
    onDismiss: () -> Unit,
    onTakePhoto: () -> Unit,
    onPickFromGallery: () -> Unit,
    onDeletePhoto: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        EditAvatarDialogContent(onDismiss, onTakePhoto, onPickFromGallery, onDeletePhoto)
    }

}

@Composable
fun EditAvatarDialogContent(
    onDismiss: () -> Unit,
    onTakePhoto: () -> Unit,
    onPickFromGallery: () -> Unit,
    onDeletePhoto: () -> Unit
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
                text = stringResource(id = R.string.edit_avatar),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 16.dp)
                    .clickable(onClick = onTakePhoto),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFCFB8FF))
                        .padding(6.dp),
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.camera),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = stringResource(R.string.make_photo),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.SansSerif
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            HorizontalDivider(
                modifier = Modifier.padding(start = 64.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 16.dp)
                    .clickable(onClick = onPickFromGallery),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFCFB8FF))
                        .padding(6.dp),
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.gallery),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = stringResource(R.string.choose_photo_gallery),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.SansSerif
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            HorizontalDivider(
                modifier = Modifier.padding(start = 64.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 16.dp)
                    .clickable(onClick = onDeletePhoto),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFCD4D7))
                        .padding(6.dp),
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.delete),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = stringResource(R.string.delete_photo),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFB3662),
                    fontFamily = FontFamily.SansSerif
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            HorizontalDivider(
                modifier = Modifier.padding(start = 64.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            CancelButton(
                text = stringResource(id = R.string.cancel),
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditAvatarDialogPreview() {
    EditAvatarDialogContent(
        onDismiss = {},
        onTakePhoto = {},
        onDeletePhoto = {},
        onPickFromGallery = {})
}