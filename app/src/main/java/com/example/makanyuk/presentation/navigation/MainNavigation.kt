package com.example.makanyuk.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.makanyuk.presentation.home.HomeScreen
import com.example.makanyuk.presentation.navigation.comps.BottomNavBar
import com.example.makanyuk.presentation.saved.SavedScren
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.ui.theme.StarterProjectTheme
import androidx.compose.material.FabPosition
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.toRoute
import com.example.makanyuk.presentation.add.AddScreen
import com.example.makanyuk.presentation.home.DetailScreen
import com.example.makanyuk.presentation.notif.NotifScreen
import com.example.makanyuk.presentation.profile.ProfileScreen
import com.example.makanyuk.presentation.saved.SavedDetailScreen

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar (
                modifier = modifier.height(70.dp),
                cutoutShape = CircleShape,
                backgroundColor = Color.White,
                elevation = 22.dp
            ){
                BottomNavBar(
                    selectedItem = selectedItem,
                    onItemSelected = {
                        selectedItem = it
                        when (it) {
                            0 -> navigateToTab(navController, HomeRoute)
                            1 -> navigateToTab(navController, SavedRoute)
                            2 -> navigateToTab(navController, NotifRoute)
                            3 -> navigateToTab(navController, ProfileRoute)
                        }
                    },
                )
            }

        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                        AddRoute.let {
                            navController.navigate(it){
                                popUpTo(it) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                },
                containerColor = Primary100,
                modifier = modifier.size(50.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "", tint = Color.White)
            }
        },

    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = modifier.padding(bottom = bottomPadding)
        ) {
            composable<HomeRoute> {
                HomeScreen(
                    navigateDetail = {
                        navController.navigate(DetailRoute(
                            id = it
                        ))
                    }
                )
            }
            composable<SavedRoute>{
                SavedScren(
                    navigateDetail = {
                        navController.navigate((SavedDetailRoute(
                            id = it
                        )))
                    }
                )
            }
            composable<DetailRoute>{
                val id = it.toRoute<DetailRoute>()
                DetailScreen(
                    id = id.id,
                    navController = navController
                )
            }
            composable<NotifRoute>{
                NotifScreen()
            }
            composable<ProfileRoute>{
                ProfileScreen()
            }
            composable<AddRoute>{
                AddScreen()
            }
            composable<SavedDetailRoute>{
                val id = it.toRoute<SavedDetailRoute>()
                SavedDetailScreen(
                    id = id.id,
                    navController = navController
                )
            }

            
        }
    }
}

private fun navigateToTab(
    navController: NavController,
    route: AppRoute
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homescreen ->
            popUpTo(homescreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun test() {
    StarterProjectTheme {
        MainNavigation()
    }
}