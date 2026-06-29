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

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    isLoggedIn: Boolean?
) {


    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn == false) {
            navController.navigate(Screens.LoginScreen) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    if (isLoggedIn == null) return

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Screens.HomeScreen else Screens.LoginScreen
    ) {
        composable<Screens.LoginScreen> {
            LoginScreen( onScreenAction = {
                navController.navigate(Screens.HomeScreen){
                    popUpTo<Screens.LoginScreen> { inclusive = true }
                }
            })
        }

        composable<Screens.HomeScreen> {
            HomeScreen()
        }
    }

}







