package com.example.makanyuk.domain.recipe.model

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)