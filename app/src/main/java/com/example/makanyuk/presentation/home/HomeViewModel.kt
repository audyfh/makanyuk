package com.example.makanyuk.presentation.home

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.domain.recipe.Result
import com.example.makanyuk.domain.recipe.repo.RecipeRepo
import com.example.makanyuk.domain.repository.RoomRepository
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
    private val repo: RecipeRepo,
    private val roomRepository: RoomRepository
) : ViewModel() {

    private var _recipes = MutableStateFlow<Resource<List<Recipe>>>(Resource.Loading())
    val recipes: StateFlow<Resource<List<Recipe>>> = _recipes.asStateFlow()

    private var _singleRecipe = MutableStateFlow<Resource<Recipe>>(Resource.Loading())
    val singleRecipe: StateFlow<Resource<Recipe>> = _singleRecipe.asStateFlow()

    private var _savedState = MutableStateFlow(false)
    val savedState : StateFlow<Boolean> = _savedState.asStateFlow()

    private var _searchResult = MutableStateFlow<Resource<List<Result>>>(Resource.Loading())
    val searchResult : StateFlow<Resource<List<Result>>> = _searchResult.asStateFlow()


    init {
        getRecipes()
    }

    fun getSavedState(id:Int){
        viewModelScope.launch {
            roomRepository.isSaved(id)
        }
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
            Log.d("FindRecipe",data.toString())
            if (data != null) {
                _singleRecipe.value = Resource.Success(data)
            } else {
                repo.getRecipeById(id).collect {
                    _singleRecipe.value = it
                }
            }
        }
    }

    fun toggleSave(recipe: Recipe){
        viewModelScope.launch {
            val saved = roomRepository.isSaved(recipe.id)
            if (saved){
                roomRepository.deleteRecipe(recipe)
                _savedState.value = false
            } else {
                roomRepository.insertRecipe(recipe)
                _savedState.value = true
            }
        }
    }

    fun searchRecipe(query: String) {
        viewModelScope.launch {
            repo.searchRecipe(query).collectLatest {
                _searchResult.value = it
            }
        }
    }
}