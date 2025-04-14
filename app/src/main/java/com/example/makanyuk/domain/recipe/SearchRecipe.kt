package com.example.makanyuk.domain.recipe

data class SearchRecipe(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)