package com.example.makanyuk.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.domain.recipe.repo.RecipeRepo
import com.example.makanyuk.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: RecipeRepo
) : ViewModel() {

    private var _recipes = MutableStateFlow<Resource<List<Recipe>>>(Resource.Loading())
    val recipes: StateFlow<Resource<List<Recipe>>> = _recipes.asStateFlow()

    private var _singleRecipe = MutableStateFlow<Resource<Recipe>>(Resource.Loading())
    val singleRecipe: StateFlow<Resource<Recipe>> = _singleRecipe.asStateFlow()


    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            repo.getRecipes().collectLatest { result ->
                _recipes.value = result
            }
        }
    }

    fun getDetailRecipe(id: Int) {
        viewModelScope.launch {
            _singleRecipe.value = Resource.Loading()
            val data = _recipes.value.data?.find {
                it.id == id
            }
            if (data != null) {
                _singleRecipe.value = Resource.Success(data)
            } else {
                repo.getRecipeById(id).collect {
                    _singleRecipe.value = it
                }
            }
        }
    }
}