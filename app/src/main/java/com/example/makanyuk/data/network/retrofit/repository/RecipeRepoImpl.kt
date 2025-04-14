package com.example.makanyuk.data.network.retrofit.repository

import android.util.Log
import com.example.makanyuk.data.mapper.NetworkMapper
import com.example.makanyuk.data.network.retrofit.ApiService
import com.example.makanyuk.domain.mealplan.DayMeal
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.domain.mealplan.MealPlan
import com.example.makanyuk.domain.recipe.Result
import com.example.makanyuk.domain.recipe.repo.RecipeRepo
import com.example.makanyuk.util.Resource
import com.example.makanyuk.util.Utility
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRepoImpl @Inject constructor(
    private val apiService: ApiService
) : RecipeRepo {
    override suspend fun getRecipes(): Flow<Resource<List<Recipe>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = apiService.getRecipes()
                val json = Gson().toJson(response.body())
                Log.d("dataresep", json)
                if (response.isSuccessful) {
                    val data = response.body()?.recipes?.map {
                        NetworkMapper.responseToDomain(it)
                    }
                    Log.d("dataresep",data.toString())
                    if (data.isNullOrEmpty()) {
                        emit(Resource.Error("No recipes found"))
                    } else {
                        emit(Resource.Success(data))
                    }
                } else {
                    emit(Resource.Error("Error ${response.code()}: ${response.message()}"))
                }
            } catch (e: Exception) {
                emit(Resource.Error("Exception: ${e.localizedMessage}"))
            }
        }
    }

    override suspend fun getRecipeById(id: Int): Flow<Resource<Recipe>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = apiService.getRecipeId(id)
                if (response.isSuccessful) {
                    val data = response.body()?.let { NetworkMapper.responseToDomain(recipe = it) }
                    emit(Resource.Success(data))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }

    override suspend fun getMealPlan(): Flow<Resource<List<DayMeal>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = apiService.getMealPlan()
                if (response.isSuccessful) {
                    val res = response.body()?.let { NetworkMapper.mealResponseToDomain(it) }
                    val data = Utility.toDayMeal(res!!.week)
                    emit(Resource.Success(data))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }

    override suspend fun searchRecipe(query: String): Flow<Resource<List<Result>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = apiService.searchRecipe(query)
                if (response.isSuccessful){
                    val data = response.body()?.let { NetworkMapper.searchResponseToDomain(it) }
                    emit(Resource.Success(data?.results))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }


}