package com.example.makanyuk.domain.mealplan

data class Week(
    val friday: Friday,
    val monday: Monday,
    val saturday: Saturday,
    val sunday: Sunday,
    val thursday: Thursday,
    val tuesday: Tuesday,
    val wednesday: Wednesday
)