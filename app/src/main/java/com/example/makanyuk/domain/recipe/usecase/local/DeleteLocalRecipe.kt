package com.example.makanyuk.domain.recipe.usecase.local

import com.example.makanyuk.domain.recipe.model.Recipe
import com.example.makanyuk.domain.recipe.repo.RoomRecipeRepository

class DeleteLocalRecipe(
    private val roomRecipeRepository: RoomRecipeRepository
) {
    suspend operator fun invoke(recipe : Recipe) = roomRecipeRepository.deleteRecipe(recipe)
}