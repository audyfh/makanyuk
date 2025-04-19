package com.example.makanyuk.domain.recipe.usecase.local

import com.example.makanyuk.domain.recipe.model.Recipe
import com.example.makanyuk.domain.recipe.repo.RoomRecipeRepository
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

class GetLocalRecipes(
    private val roomRecipeRepository: RoomRecipeRepository
) {
    suspend operator fun invoke() : Flow<Resource<List<Recipe>>>
        = roomRecipeRepository.getAllRecipe()
}