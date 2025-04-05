package com.example.makanyuk.data.mapper

import com.example.makanyuk.data.network.retrofit.MealPlanResponse
import com.example.makanyuk.data.network.retrofit.RecipeResponse
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.domain.recipe.mealplan.MealPlan

object NetworkMapper {
    
    fun responseToDomain(recipe: RecipeResponse) : Recipe {
        return Recipe(
            aggregateLikes = recipe.aggregateLikes ?: 0,
            analyzedInstructions = recipe.analyzedInstructions ?: emptyList(),
            cheap = recipe.cheap ?: false,
            cookingMinutes = recipe.cookingMinutes ?: 0,
            creditsText = recipe.creditsText ?: "",
            cuisines = recipe.cuisines ?: emptyList(),
            dairyFree = recipe.dairyFree ?: false,
            diets = recipe.diets ?: emptyList(),
            dishTypes = recipe.dishTypes ?: emptyList(),
            extendedIngredients = recipe.extendedIngredients ?: emptyList(),
            gaps = recipe.gaps ?: "",
            glutenFree = recipe.glutenFree ?: false,
            healthScore = recipe.healthScore ?: 0.0,
            id = recipe.id ?: 0,
            image = recipe.image ?: "",
            imageType = recipe.imageType ?: "",
            instructions = recipe.instructions ?: "",
            license = recipe.license ?: "",
            lowFodmap = recipe.lowFodmap ?: false,
            occasions = recipe.occasions ?: emptyList(),
            originalId = recipe.originalId ?: 0,
            preparationMinutes = recipe.preparationMinutes ?: 0,
            pricePerServing = recipe.pricePerServing ?: 0.0,
            readyInMinutes = recipe.readyInMinutes ?: 0,
            servings = recipe.servings ?: 1,
            sourceName = recipe.sourceName ?: "",
            sourceUrl = recipe.sourceUrl ?: "",
            spoonacularScore = recipe.spoonacularScore ?: 0.0,
            spoonacularSourceUrl = recipe.spoonacularSourceUrl ?: "",
            summary = recipe.summary ?: "",
            sustainable = recipe.sustainable ?: false,
            title = recipe.title ?: "",
            vegan = recipe.vegan ?: false,
            vegetarian = recipe.vegetarian ?: false,
            veryHealthy = recipe.veryHealthy ?: false,
            veryPopular = recipe.veryPopular ?: false,
            weightWatcherSmartPoints = recipe.weightWatcherSmartPoints ?: 0
        )
    }

    fun domainToResponse(recipe: Recipe) : RecipeResponse {
        return RecipeResponse(
            aggregateLikes = recipe.aggregateLikes,
            analyzedInstructions = recipe.analyzedInstructions,
            cheap = recipe.cheap,
            cookingMinutes = recipe.cookingMinutes,
            creditsText = recipe.creditsText,
            cuisines = recipe.cuisines,
            dairyFree = recipe.dairyFree,
            diets = recipe.diets,
            dishTypes = recipe.dishTypes,
            extendedIngredients = recipe.extendedIngredients,
            gaps = recipe.gaps,
            glutenFree = recipe.glutenFree,
            healthScore = recipe.healthScore,
            id = recipe.id,
            image = recipe.image,
            imageType = recipe.imageType,
            instructions = recipe.instructions,
            license = recipe.license,
            lowFodmap = recipe.lowFodmap,
            occasions = recipe.occasions,
            originalId = recipe.originalId,
            preparationMinutes = recipe.preparationMinutes,
            pricePerServing = recipe.pricePerServing,
            readyInMinutes = recipe.readyInMinutes,
            servings = recipe.servings,
            sourceName = recipe.sourceName,
            sourceUrl = recipe.sourceUrl,
            spoonacularScore = recipe.spoonacularScore,
            spoonacularSourceUrl = recipe.spoonacularSourceUrl,
            summary = recipe.summary,
            sustainable = recipe.sustainable,
            title = recipe.title,
            vegan = recipe.vegan,
            vegetarian = recipe.vegetarian,
            veryHealthy = recipe.veryHealthy,
            veryPopular = recipe.veryPopular,
            weightWatcherSmartPoints = recipe.weightWatcherSmartPoints
        )
    }

    fun mealResponseToDomain(meal: MealPlanResponse) : MealPlan {
        return MealPlan(
            week = meal.week
        )
    }

    fun mealDomainToResponse(meal: MealPlan) : MealPlanResponse {
        return MealPlanResponse(
            week = meal.week
        )
    }
}