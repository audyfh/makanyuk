package com.example.makanyuk.data.local

import com.example.makanyuk.data.mapper.RecipeMapper
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.domain.repository.RoomRepository
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomRepoImpl @Inject constructor(
    private val recipeDao: RecipeDao
) : RoomRepository {
    override suspend fun insertRecipe(recipe: Recipe) {
       recipeDao.insertRecipe(
           RecipeMapper.domainToEntity(recipe)
       )
    }

    override suspend fun getAllRecipe():Flow<Resource<List<Recipe>>> {
        return flow {
            emit(Resource.Loading())
            val data = recipeDao.getAllRecipe().map {
                it.map {
                    RecipeMapper.entityToDomain(it)
                }
            }
            data.collect{
                emit(Resource.Success(it))
            }

        }
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(
            RecipeMapper.domainToEntity(recipe)
        )
    }

    override suspend fun getRecipeById(id: Int): Resource<Recipe> {
        val recipe = recipeDao.getRecipeById(id)?.let { RecipeMapper.entityToDomain(it) }
        return Resource.Success(recipe)
    }

    override suspend fun isSaved(id: Int): Boolean {
        return recipeDao.getRecipeById(id) != null
    }
}