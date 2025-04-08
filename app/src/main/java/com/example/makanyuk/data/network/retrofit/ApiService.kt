package com.example.makanyuk.data.network.retrofit

import com.example.makanyuk.BuildConfig
import com.example.makanyuk.domain.recipe.mealplan.MealPlan
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.domain.recipe.RecipeModel
import com.example.makanyuk.util.Constants
import com.google.firestore.v1.ListenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("recipes/random?apiKey=${BuildConfig.Recipe_Key}&number=10")
    suspend fun getRecipes() : Response<RecipeListResponse>

    @GET("recipes/{id}/information?apiKey=${BuildConfig.Recipe_Key}")
    suspend fun getRecipeId(
        @Path("id") id: Int
    ) : Response<RecipeResponse>

    @GET("mealplanner/generate?apiKey=${BuildConfig.Recipe_Key}")
    suspend fun getMealPlan() : Response<MealPlanResponse>

    companion object{
        const val BASE_URL = "https://api.spoonacular.com/"
    }

}