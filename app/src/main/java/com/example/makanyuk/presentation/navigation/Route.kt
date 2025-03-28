package com.example.makanyuk.presentation.navigation

sealed class Route(
    val route: String
) {

    data object MainNavigation : Route("mainNavigation")

    data object LoginScreen : Route("loginScreen")
    data object RegisterScreen : Route("registerScreen")
    data object HomeScreen : Route("homeScreen")
    data object SavedScreen : Route("savedScreen")
    data object NotifScreen : Route("notifScreen")
    data object ProfileScreen : Route("profileScreen")
    data object AddScreen : Route("addScreenRoute")
}