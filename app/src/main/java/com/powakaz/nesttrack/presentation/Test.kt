package com.powakaz.nesttrack.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.powakaz.nesttrack.ui.theme.NestTrackTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Test : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NestTrackTheme {

            }
        }
    }
}
