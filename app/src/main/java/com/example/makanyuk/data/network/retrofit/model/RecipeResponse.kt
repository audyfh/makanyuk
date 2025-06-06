package com.example.makanyuk.data.network.retrofit.model

import com.example.makanyuk.domain.recipe.model.AnalyzedInstruction
import com.example.makanyuk.domain.recipe.model.ExtendedIngredient

data class RecipeResponse(
    val aggregateLikes: Int?,
    val analyzedInstructions: List<AnalyzedInstruction>?,
    val cheap: Boolean?,
    val cookingMinutes: Int?,
    val creditsText: String?,
    val cuisines: List<String>?,
    val dairyFree: Boolean?,
    val diets: List<String>?,
    val dishTypes: List<String>?,
    val extendedIngredients: List<ExtendedIngredient>?,
    val gaps: String?,
    val glutenFree: Boolean?,
    val healthScore: Double?,
    val id: Int?,
    val image: String?,
    val imageType: String?,
    val instructions: String?,
    val license: String?,
    val lowFodmap: Boolean?,
    val occasions: List<String>?,
    val originalId: Int?,
    val preparationMinutes: Int?,
    val pricePerServing: Double?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val sourceName: String?,
    val sourceUrl: String?,
    val spoonacularScore: Double?,
    val spoonacularSourceUrl: String?,
    val summary: String?,
    val sustainable: Boolean?,
    val title: String?,
    val vegan: Boolean?,
    val vegetarian: Boolean?,
    val veryHealthy: Boolean?,
    val veryPopular: Boolean?,
    val weightWatcherSmartPoints: Int?
)
