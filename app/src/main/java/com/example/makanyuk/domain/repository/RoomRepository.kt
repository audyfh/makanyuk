package com.example.makanyuk.domain.repository

import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun insertRecipe(recipe: Recipe)
    fun getAllRecipe() : Flow<Resource<List<Recipe>>>
    suspend fun deleteRecipe(recipe: Recipe)
}