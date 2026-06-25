package com.powakaz.nesttrack.feature_profile.presentation.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.powakaz.nesttrack.feature_profile.R

@Composable
fun CancelButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CancelButtonContent(text, onClick)
}

@Composable
fun CancelButtonContent(text: String, onClick: () -> Unit){
    TextButton(
        onClick = onClick,
        modifier = Modifier.size(width = 140.dp, height = 50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCFB8FF))

    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFA17CDE),
            fontFamily = FontFamily.SansSerif
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CancelButtonPreview(){
    CancelButtonContent(text = "",
        onClick = {})
}