package com.example.makanyuk.domain.recipe.usecase.local

import com.example.makanyuk.domain.recipe.repo.RoomRecipeRepository

class IsLocalRecipeSaved(
    private val roomRecipeRepository: RoomRecipeRepository
) {
    suspend operator fun invoke(id: Int) : Boolean = roomRecipeRepository.isSaved(id)
}