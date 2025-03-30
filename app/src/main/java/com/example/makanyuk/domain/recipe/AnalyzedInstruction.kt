package com.example.makanyuk.domain.recipe

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)