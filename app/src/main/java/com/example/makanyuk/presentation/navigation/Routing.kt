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
object NotifRoute : AppRoute

@Serializable
object ProfileRoute : AppRoute

@Serializable
object AddRoute : AppRoute