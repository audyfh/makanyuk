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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.makanyuk.presentation.add.AddScreen
import com.example.makanyuk.presentation.ai.AiScreen
import com.example.makanyuk.presentation.ai.TrackScreen
import com.example.makanyuk.presentation.home.DetailScreen
import com.example.makanyuk.presentation.home.SearchScreen
import com.example.makanyuk.presentation.mealplan.MealPlanScreen

import com.example.makanyuk.presentation.profile.ProfileScreen
import com.example.makanyuk.presentation.saved.SavedDetailScreen

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navigateLogin : () -> Unit
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
                            2 -> navigateToTab(navController, MealPlanRoute)
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
                    selectedItem = -1
                       navController.navigate(AI){
                           popUpTo(navController.graph.findStartDestination().id){
                               saveState = true
                           }
                           launchSingleTop = true
                           restoreState = true
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
                    },
                    navigateSearch = {
                        navController.navigate(SearchRoute(
                            query = it
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
            composable<MealPlanRoute>{
                MealPlanScreen()
            }
            composable<ProfileRoute>{
                ProfileScreen(
                    navigateLogin = navigateLogin
                )
            }
            navigation<AI>(startDestination = AIRoute){
                composable<AIRoute>{
                    AiScreen(navController = navController)
                }
                composable<TrackRoute>{
                    TrackScreen()
                }
            }
            composable<SavedDetailRoute>{
                val id = it.toRoute<SavedDetailRoute>()
                SavedDetailScreen(
                    id = id.id,
                    navController = navController
                )
            }
            composable<SearchRoute> {
                val query = it.toRoute<SearchRoute>()
                SearchScreen(query = query.query){

                }
            }


            
        }
    }
}

private fun navigateToTab(
    navController: NavController,
    route: AppRoute
) {
    navController.navigate(route) {
       popUpTo(0){
           saveState = true
       }
        launchSingleTop = true
        restoreState = true
    }
}

