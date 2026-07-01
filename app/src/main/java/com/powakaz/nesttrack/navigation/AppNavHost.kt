package com.powakaz.nesttrack.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.powakaz.feature_auth.presentation.LoginScreen
import com.powakaz.feature_home.HomeScreen
import com.powakaz.navigation_api.Screens

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    isLoggedIn: Boolean?
) {

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn == false || isLoggedIn == null) {
            navController.navigate(Screens.LoginScreen) {
                popUpTo(0) { inclusive = true }
            }
        }
    }



    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn != null && isLoggedIn) Screens.HomeScreen else Screens.LoginScreen
    ) {
        composable<Screens.LoginScreen> {
            LoginScreen()
        }

        composable<Screens.HomeScreen> {
            HomeScreen()
        }
    }

}







