package com.example.makanyuk.domain.recipe.model.mealplan

data class Meal(
    val id: Int,
    val image: String,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)