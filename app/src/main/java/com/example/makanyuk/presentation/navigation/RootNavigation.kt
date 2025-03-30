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
    startDestiation : AppRoute
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestiation
    ) {
        composable<LoginRoute>{
            LoginScreen(
                navController = navController
            )
        }
        composable<RegisterRoute>{
            RegisterScreen(
                navController = navController
            )
        }
        composable<MainRoute>{
            MainNavigation()
        }
    }
}