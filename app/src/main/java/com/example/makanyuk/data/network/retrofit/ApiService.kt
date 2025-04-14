package com.example.makanyuk.data.network.retrofit

import com.example.makanyuk.BuildConfig
import com.example.makanyuk.data.network.retrofit.model.MealPlanResponse
import com.example.makanyuk.data.network.retrofit.model.RecipeListResponse
import com.example.makanyuk.data.network.retrofit.model.RecipeResponse
import com.example.makanyuk.data.network.retrofit.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("recipes/random?apiKey=${BuildConfig.Recipe_Key}&number=10")
    suspend fun getRecipes() : Response<RecipeListResponse>

    @GET("recipes/{id}/information?apiKey=${BuildConfig.Recipe_Key}")
    suspend fun getRecipeId(
        @Path("id") id: Int
    ) : Response<RecipeResponse>

    @GET("mealplanner/generate?apiKey=${BuildConfig.Recipe_Key}")
    suspend fun getMealPlan() : Response<MealPlanResponse>

    @GET("recipes/complexSearch?apiKey=${BuildConfig.Recipe_Key}")
    suspend fun searchRecipe(
        @Query("query") query: String
    ) : Response<SearchResponse>

    companion object{
        const val BASE_URL = "https://api.spoonacular.com/"
    }

}