package com.example.makanyuk.di

import android.content.Context
import androidx.room.Room
import com.example.makanyuk.BuildConfig
import com.example.makanyuk.data.local.RecipeDB
import com.example.makanyuk.data.local.RecipeDao
import com.example.makanyuk.data.network.retrofit.ApiService
import com.example.makanyuk.domain.recipe.Recipe
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSpoonacularAPI() : ApiService{
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideRecipeDB(
        @ApplicationContext context : Context
    ) : RecipeDB = Room.databaseBuilder(context, RecipeDB::class.java,"Recipe_DB").build()

    @Provides
    @Singleton
    fun provideRecipeDao(
        db : RecipeDB
    ) : RecipeDao = db.recipeDao()

    @Provides
    @Singleton
    fun provideGemini() : GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.Gemini_Key
    )

}