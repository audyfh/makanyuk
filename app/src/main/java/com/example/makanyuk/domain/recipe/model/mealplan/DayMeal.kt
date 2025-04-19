package com.example.makanyuk.domain.recipe.model.mealplan

data class DayMeal(
    val day : String,
    val meal : List<Meal>,
    val nutrients: Nutrients
)