package com.example.makanyuk.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makanyuk.domain.recipe.model.Recipe
import com.example.makanyuk.domain.recipe.usecase.local.LocalRecipeUseCases
import com.example.makanyuk.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val localRecipeUseCases: LocalRecipeUseCases
) : ViewModel() {

    private var _recipes = MutableStateFlow<Resource<List<Recipe>>>(Resource.Loading())
    val recipes : StateFlow<Resource<List<Recipe>>> = _recipes.asStateFlow()

    private var _singleRecipe = MutableStateFlow<Resource<Recipe>>(Resource.Loading())
    val singleRecipe : StateFlow<Resource<Recipe>> = _singleRecipe.asStateFlow()

    init {
        getAllRecipes()
    }

    private fun getAllRecipes(){
        viewModelScope.launch {
            localRecipeUseCases.getLocalRecipes().collectLatest {
                _recipes.value = it
            }
        }
    }

    fun toggleSave(recipe: Recipe){
        viewModelScope.launch {
            val saved = localRecipeUseCases.isLocalRecipeSaved(recipe.id)
            if (saved){
                localRecipeUseCases.deleteLocalRecipe(recipe)
            } else {
                localRecipeUseCases.insertLocalRecipe(recipe)
            }
        }
    }

    fun getRecipeDetail(id: Int){
        viewModelScope.launch {
            val data = localRecipeUseCases.getLocalRecipeByID(id)
            _singleRecipe.value = data
        }
    }
}