package com.example.makanyuk.domain.recipe.usecase.network

import com.example.makanyuk.domain.recipe.model.mealplan.DayMeal
import com.example.makanyuk.domain.recipe.repo.RecipeRepo
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

class GetMealPlanUseCase (
    private val recipeRepo: RecipeRepo
) {
    suspend operator fun invoke () : Flow<Resource<List<DayMeal>>> = recipeRepo.getMealPlan()
}