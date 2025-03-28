package com.example.makanyuk.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makanyuk.model.recipe.Recipe
import com.example.makanyuk.model.recipe.RecipeResponse
import com.example.makanyuk.model.recipe.repo.RecipeRepo
import com.example.makanyuk.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo : RecipeRepo
) : ViewModel() {

    private var _recipes = MutableStateFlow<Resource<List<Recipe>>>(Resource.Loading())
    val recipes : StateFlow<Resource<List<Recipe>>> = _recipes.asStateFlow()

    init {
        getRecipes()
    }

    private fun getRecipes(){
        viewModelScope.launch {
            repo.getRecipes().collectLatest { result ->
                _recipes.value = result
            }
        }
    }
}