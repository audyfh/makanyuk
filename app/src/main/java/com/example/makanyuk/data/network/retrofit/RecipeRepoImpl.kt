package com.example.makanyuk.data.network.retrofit

import com.example.makanyuk.model.recipe.Recipe
import com.example.makanyuk.model.recipe.RecipeResponse
import com.example.makanyuk.model.recipe.mealplan.MealPlan
import com.example.makanyuk.model.recipe.repo.RecipeRepo
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
                if (response.isSuccessful) {
                    val data = response.body()?.recipes
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

    override suspend fun getRecipeById(id: String): Flow<Resource<Recipe>> {
        return flow {
            try {
                emit(Resource.Loading())
                val data = apiService.getRecipeId(id)
                emit(Resource.Success(data.body()))
            } catch (e: Exception){
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }

    override suspend fun getMealPlan(): Flow<Resource<MealPlan>> {
        return flow {
            try {
                emit(Resource.Loading())
                val data = apiService.getMealPlan()
                emit(Resource.Success(data.body()))
            } catch (e: Exception){
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }
}