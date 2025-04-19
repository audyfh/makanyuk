package com.example.makanyuk.domain.recipe.usecase.network

data class RecipeUseCases(
    val getRecipesUseCase: GetRecipesUseCase,
    val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    val getMealPlanUseCase : GetMealPlanUseCase,
    val searchRecipeUseCase: SearchRecipeUseCase
)
