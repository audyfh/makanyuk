package com.example.makanyuk.data.network.retrofit.model

import com.example.makanyuk.domain.recipe.Result

data class SearchResponse(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)
