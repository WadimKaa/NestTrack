package com.powakaz.feature_auth.presentation.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.powakaz.feature_auth.R


@Preview
@Composable
fun SplashContent() {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.img_nest_splash),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.BottomCenter
                    )
                    .padding(bottom = 64.dp)
            )
            Text(
                text = "Nest Tracker",
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 250.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 56.sp
            )
        }
    }
}