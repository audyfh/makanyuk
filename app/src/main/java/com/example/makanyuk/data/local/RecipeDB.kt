package com.example.makanyuk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntity::class], version = 1)
abstract class RecipeDB : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}