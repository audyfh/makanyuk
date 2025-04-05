package com.example.makanyuk.data.mapper

import com.example.makanyuk.data.network.retrofit.MealPlanResponse
import com.example.makanyuk.data.network.retrofit.RecipeResponse
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.domain.recipe.mealplan.MealPlan

object NetworkMapper {
    
    fun responseToDomain(recipe: RecipeResponse) : Recipe {
        return Recipe(
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