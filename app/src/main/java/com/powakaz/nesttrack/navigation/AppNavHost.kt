package com.powakaz.nesttrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.powakaz.feature_auth.presentation.LoginScreen
import com.powakaz.feature_home.HomeScreen
import com.powakaz.navigation_api.Screens
import com.powakaz.nesttrack.feature_profile.presentation.screen.ProfileScreen
import com.powakaz.nesttrack.presentation.LoginState

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    loginState: LoginState
) {


    LaunchedEffect(loginState) {
        when(loginState){
            LoginState.INITIAL -> {}
            LoginState.LOGINED -> {
                navController.navigate(Screens.HomeScreen) {
                    popUpTo(0) { inclusive = true }
                }
            }
            LoginState.NOT_LOGINED -> {
                navController.navigate(Screens.LoginScreen) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }



    NavHost(
        navController = navController,
        startDestination = if (loginState == LoginState.LOGINED) Screens.HomeScreen else Screens.LoginScreen
    ) {

        composable<Screens.LoginScreen> {
           LoginScreen()
        }

        composable<Screens.HomeScreen> {
            HomeScreen()
        }
    }

}







