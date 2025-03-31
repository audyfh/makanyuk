package com.example.makanyuk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
@TypeConverters(RecipeConverter::class)
abstract class RecipeDB : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}