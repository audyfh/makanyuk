package com.example.makanyuk.domain.recipe.usecase.local

data class LocalRecipeUseCases(
    val getLocalRecipes: GetLocalRecipes,
    val getLocalRecipeByID: GetLocalRecipeByID,
    val deleteLocalRecipe: DeleteLocalRecipe,
    val insertLocalRecipe: InsertLocalRecipe,
    val isLocalRecipeSaved: IsLocalRecipeSaved
)
