package com.example.makanyuk.data.local

import androidx.room.TypeConverter
import com.example.makanyuk.domain.recipe.model.AnalyzedInstruction
import com.example.makanyuk.domain.recipe.model.ExtendedIngredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value : List<String>?) : String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String) : List<String> {
        return gson.fromJson(value,object  : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromAnalyzedInstructions(value: List<AnalyzedInstruction?>) : String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAnalyzedInstruction(value: String) : List<AnalyzedInstruction> {
        return gson.fromJson(value, object : TypeToken<List<AnalyzedInstruction>>() {}.type)
    }

    @TypeConverter
    fun fromExtendedIngredients(value: List<ExtendedIngredient>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toExtendedIngredients(value: String): List<ExtendedIngredient> {
        return gson.fromJson(value, object : TypeToken<List<ExtendedIngredient>>() {}.type)
    }
}