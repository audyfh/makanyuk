package com.example.makanyuk.data.network.retrofit

import com.example.makanyuk.model.recipe.mealplan.MealPlan
import com.example.makanyuk.model.recipe.Recipe
import com.example.makanyuk.model.recipe.RecipeResponse
import com.example.makanyuk.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("recipes/random?apiKey=${Constants.apiKey}&number=10")
    suspend fun getRecipes() : Response<RecipeResponse>

    @GET("{id}/information?apiKey=${Constants.apiKey}")
    suspend fun getRecipeId(
        @Path("id") id: String
    ) : Response<Recipe>

    @GET("mealplanner/generate?apiKey=${Constants.apiKey}")
    suspend fun getMealPlan() : Response<MealPlan>

    companion object{
        const val BASE_URL = "https://api.spoonacular.com/"
    }

}