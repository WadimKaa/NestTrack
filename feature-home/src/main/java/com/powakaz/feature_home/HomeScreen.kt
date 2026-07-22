package com.powakaz.feature_home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.powakaz.nesttrack.feature_profile.presentation.screen.ProfileScreen
import com.powakaz.nesttrack.feature_time.pres.screen.TimeTrackingScreen


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(homeScreenUiState = state, onEvent = viewModel::onEvent)
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewHomeScreen(){
    HomeScreenContent(HomeScreenUiState(), {})
}

data class BottomNavItem(
    val label: String,
    val activeIcon: Int,
    val inactiveIcon: Int
)

val navItems = listOf(
    BottomNavItem("Кошелек", R.drawable.ic_wallet_active, R.drawable.ic_wallet_inactive),
    BottomNavItem("Время", R.drawable.ic_time_active, R.drawable.ic_time_inactive),
    BottomNavItem("Профиль", R.drawable.ic_human_active, R.drawable.ic_human_inactive)
)



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreenContent(homeScreenUiState: HomeScreenUiState, onEvent: (HomeScreenUiEvent) -> Unit){
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                navItems.forEachIndexed { index, item ->
                    val isSelected = homeScreenUiState.selectedItem == index
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = if (isSelected) item.activeIcon else item.inactiveIcon),
                                contentDescription = item.label,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(28.dp)
                            )
                        },
                        label = { Text(text = item.label, color = if (isSelected) Color(0XFF2f6eb2) else Color(0XFF4175a4)) },
                        selected = isSelected,
                        onClick = { onEvent(HomeScreenUiEvent.SelectItem(index)) },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {

            when(homeScreenUiState.selectedItem) {
                0 -> {}
                1 -> TimeTrackingScreen()
                2 -> ProfileScreen()
            }

        }
    }
}