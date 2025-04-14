package com.example.makanyuk.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.makanyuk.domain.mealplan.DayMeal
import com.example.makanyuk.domain.mealplan.Meal
import com.example.makanyuk.domain.mealplan.Week
import java.io.IOException

object Utility {

    fun extractJsonFromCodeBlock(response: String): String? {
        val regex = Regex("""```json\s*(\{.*?})\s*```""", RegexOption.DOT_MATCHES_ALL)
        val match = regex.find(response)
        return match?.groups?.get(1)?.value
    }

    fun extractJsonSimple(response: String): String {
        return response
            .substringAfter("```json")
            .substringBefore("```")
            .trim()
    }

    fun toDayMeal(week: Week) : List<DayMeal> {
        return listOf(
            DayMeal(day = "Sunday", meal = week.sunday.meals, nutrients = week.sunday.nutrients),
            DayMeal(day = "Monday", meal = week.monday.meals, nutrients = week.monday.nutrients),
            DayMeal(day = "Tuesday", meal = week.tuesday.meals, nutrients = week.tuesday.nutrients),
            DayMeal(day = "Wednesday", meal = week.wednesday.meals, nutrients = week.wednesday.nutrients),
            DayMeal(day = "Thursday", meal = week.thursday.meals, nutrients = week.thursday.nutrients),
            DayMeal(day = "Friday", meal = week.friday.meals, nutrients = week.friday.nutrients),
            DayMeal(day = "Saturday", meal = week.saturday.meals, nutrients = week.saturday.nutrients),
        )
    }

    fun uriToBitmap(
        context: Context,
        uri: Uri
    ): Bitmap? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}