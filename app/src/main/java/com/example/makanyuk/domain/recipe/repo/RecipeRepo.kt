package com.example.makanyuk.domain.recipe.repo

import com.example.makanyuk.domain.recipe.model.mealplan.DayMeal
import com.example.makanyuk.domain.recipe.model.Recipe
import com.example.makanyuk.domain.recipe.model.Result
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepo {
    suspend fun getRecipes() : Flow<Resource<List<Recipe>>>
    suspend fun getRecipeById(id:Int) : Flow<Resource<Recipe>>
    suspend fun getMealPlan() : Flow<Resource<List<DayMeal>>>
    suspend fun searchRecipe(query: String) : Flow<Resource<List<Result>>>
}