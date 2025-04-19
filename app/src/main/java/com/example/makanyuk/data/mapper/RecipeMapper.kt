package com.example.makanyuk.data.mapper

import com.example.makanyuk.data.local.RecipeEntity
import com.example.makanyuk.domain.recipe.model.Recipe

object RecipeMapper {
    fun entityToDomain(recipeEntity: RecipeEntity) : Recipe {
        return Recipe(
            aggregateLikes = recipeEntity.aggregateLikes,
            analyzedInstructions = recipeEntity.analyzedInstructions,
            cheap = recipeEntity.cheap,
            cookingMinutes = recipeEntity.cookingMinutes,
            creditsText = recipeEntity.creditsText,
            cuisines = recipeEntity.cuisines,
            dairyFree = recipeEntity.dairyFree,
            diets = recipeEntity.diets,
            dishTypes = recipeEntity.dishTypes,
            extendedIngredients = recipeEntity.extendedIngredients,
            gaps = recipeEntity.gaps,
            glutenFree = recipeEntity.glutenFree,
            healthScore = recipeEntity.healthScore,
            id = recipeEntity.id,
            image = recipeEntity.image,
            imageType = recipeEntity.imageType,
            instructions = recipeEntity.instructions,
            license = recipeEntity.license,
            lowFodmap = recipeEntity.lowFodmap,
            occasions = recipeEntity.occasions,
            originalId = recipeEntity.originalId,
            preparationMinutes = recipeEntity.preparationMinutes,
            pricePerServing = recipeEntity.pricePerServing,
            readyInMinutes = recipeEntity.readyInMinutes,
            servings = recipeEntity.servings,
            sourceName = recipeEntity.sourceName,
            sourceUrl = recipeEntity.sourceUrl,
            spoonacularScore = recipeEntity.spoonacularScore,
            spoonacularSourceUrl = recipeEntity.spoonacularSourceUrl,
            summary = recipeEntity.summary,
            sustainable = recipeEntity.sustainable,
            title = recipeEntity.title,
            vegan = recipeEntity.vegan,
            vegetarian = recipeEntity.vegetarian,
            veryHealthy = recipeEntity.veryHealthy,
            veryPopular = recipeEntity.veryPopular,
            weightWatcherSmartPoints = recipeEntity.weightWatcherSmartPoints
        )
    }

    fun domainToEntity(recipe: Recipe) : RecipeEntity {
        return RecipeEntity(
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




}