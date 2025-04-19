package com.example.makanyuk.domain.recipe.usecase.local

import com.example.makanyuk.domain.recipe.model.Recipe
import com.example.makanyuk.domain.recipe.repo.RoomRecipeRepository
import com.example.makanyuk.util.Resource

class GetLocalRecipeByID(
    private val roomRecipeRepository: RoomRecipeRepository
) {
    suspend operator fun invoke(id: Int) : Resource<Recipe>
        = roomRecipeRepository.getRecipeById(id)
}