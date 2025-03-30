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

    override fun getAllRecipe():Flow<Resource<List<Recipe>>> {
        return flow {
            emit(Resource.Loading())
            recipeDao.getAllRecipe().map {
               Resource.Success(it.map { recipe ->
                   RecipeMapper.entityToDomain(recipe)
               })
            }.collectLatest { emit(it) }

        }
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(
            RecipeMapper.domainToEntity(recipe)
        )
    }
}