package com.example.makanyuk.domain.recipe.repo

import com.example.makanyuk.domain.recipe.model.Recipe
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

interface RoomRecipeRepository {

    suspend fun insertRecipe(recipe: Recipe)
    suspend fun getAllRecipe() : Flow<Resource<List<Recipe>>>
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun getRecipeById(id: Int) : Resource<Recipe>
    suspend fun isSaved(id: Int) : Boolean

}