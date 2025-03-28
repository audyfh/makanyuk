package com.example.makanyuk.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.makanyuk.presentation.auth.LoginScreen
import com.example.makanyuk.presentation.auth.RegisterScreen

@Composable
fun RootNavigation(
    modifier: Modifier = Modifier,
    startDestiation : String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestiation
    ) {
        composable(Route.LoginScreen.route){
            LoginScreen(
                navController = navController
            )
        }
        composable(Route.RegisterScreen.route){
            RegisterScreen(
                navController = navController
            )
        }
        composable(Route.MainNavigation.route){
            MainNavigation()
        }
    }
}