package com.example.makanyuk.domain.recipe.usecase.network

import com.example.makanyuk.domain.recipe.model.Recipe
import com.example.makanyuk.domain.recipe.repo.RecipeRepo
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

class GetRecipesUseCase(
    private val recipeRepo: RecipeRepo
) {
    suspend operator fun invoke () : Flow<Resource<List<Recipe>>> = recipeRepo.getRecipes()
}