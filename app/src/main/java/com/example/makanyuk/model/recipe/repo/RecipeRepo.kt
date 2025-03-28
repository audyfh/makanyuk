package com.example.makanyuk.model.recipe.repo

import com.example.makanyuk.model.recipe.Recipe
import com.example.makanyuk.model.recipe.RecipeResponse
import com.example.makanyuk.model.recipe.mealplan.MealPlan
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepo {
    suspend fun getRecipes() : Flow<Resource<List<Recipe>>>
    suspend fun getRecipeById(id:String) : Flow<Resource<Recipe>>
    suspend fun getMealPlan() : Flow<Resource<MealPlan>>
}