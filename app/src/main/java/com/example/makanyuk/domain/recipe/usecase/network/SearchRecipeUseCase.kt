package com.example.makanyuk.domain.recipe.usecase.network

import com.example.makanyuk.domain.recipe.model.Result
import com.example.makanyuk.domain.recipe.repo.RecipeRepo
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

class SearchRecipeUseCase(
    private val recipeRepo: RecipeRepo
) {
    suspend operator fun invoke(query: String): Flow<Resource<List<Result>>> =
        recipeRepo.searchRecipe(query)
}