package com.example.makanyuk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.makanyuk.presentation.auth.AuthViewModel
import com.example.makanyuk.presentation.auth.LoginScreen
import com.example.makanyuk.presentation.home.HomeScreen
import com.example.makanyuk.presentation.navigation.RootNavigation
import com.example.makanyuk.ui.theme.StarterProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarterProjectTheme {
               RootNavigation(
                 startDestiation = authViewModel.startDestination
               )
            }
        }
    }
}

