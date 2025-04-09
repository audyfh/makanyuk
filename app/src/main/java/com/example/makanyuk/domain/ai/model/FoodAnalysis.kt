package com.example.makanyuk.domain.ai.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodAnalysis(
    val foodName: String,
    val calorie: Int,
    val macronutrients : Macronutrients,
    val ingredients : List<String>
)

@Serializable
data class Macronutrients(
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val fibre: Int
)
