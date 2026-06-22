package com.powakaz.nesttrack

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppHavHost(
    navController: NavHostController = rememberNavController()
) {
    /*NavHost(
        navController = navController,
        startDestination = Screen.TodoList
    ) {

    }*/
}