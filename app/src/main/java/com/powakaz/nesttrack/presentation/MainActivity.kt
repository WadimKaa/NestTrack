package com.powakaz.nesttrack.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.powakaz.nesttrack.navigation.AppNavHost
import com.powakaz.nesttrack.ui.theme.NestTrackTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.value.loginState == LoginState.INITIAL
        }

        setContent {
            NestTrackTheme {
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                if (state.loginState != LoginState.INITIAL) {
                    AppNavHost(loginState = state.loginState)
                }
            }
        }
    }
}
