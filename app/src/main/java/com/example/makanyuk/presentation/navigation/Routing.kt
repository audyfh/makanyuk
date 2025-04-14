package com.example.makanyuk.presentation.navigation

import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
sealed interface AppRoute

@Serializable
object LoginRoute : AppRoute

@Serializable
object RegisterRoute : AppRoute

@Serializable
object MainRoute : AppRoute

@Serializable
object HomeRoute : AppRoute

@Serializable
object SavedRoute : AppRoute

@Serializable
data class DetailRoute(
    val id: Int
) : AppRoute

@Serializable
data class SavedDetailRoute(
    val id : Int
) : AppRoute

@Serializable
object NotifRoute : AppRoute

@Serializable
object ProfileRoute : AppRoute

@Serializable
object AddRoute : AppRoute

@Serializable
object AI : AppRoute

@Serializable
object AIRoute : AppRoute

@Serializable
object TrackRoute : AppRoute

@Serializable
object MealPlanRoute : AppRoute

@Serializable
data class SearchRoute(
    val query : String
) : AppRoute