package com.powakaz.navigation_api

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screens {
    @Serializable object LoginScreen : Screens
    @Serializable object HomeScreen : Screens
    @Serializable object SplashScreen : Screens

}