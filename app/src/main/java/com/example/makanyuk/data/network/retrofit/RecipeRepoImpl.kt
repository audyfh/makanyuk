package com.example.makanyuk.data.network.retrofit

import android.util.Log
import com.example.makanyuk.data.mapper.NetworkMapper
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.domain.recipe.mealplan.MealPlan
import com.example.makanyuk.domain.recipe.repo.RecipeRepo
import com.example.makanyuk.util.Resource
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
                Log.d("dataresep",response.toString())
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

    override suspend fun getMealPlan(): Flow<Resource<MealPlan>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = apiService.getMealPlan()
                if (response.isSuccessful) {
                    val data = response.body()?.let { NetworkMapper.mealResponseToDomain(it) }
                    emit(Resource.Success(data))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }


}