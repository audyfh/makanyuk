package com.example.makanyuk.domain.mealplan

data class DayMeal(
    val day : String,
    val meal : List<Meal>,
    val nutrients: Nutrients
)