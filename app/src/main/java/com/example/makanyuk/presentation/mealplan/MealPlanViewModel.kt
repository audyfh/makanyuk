package com.example.makanyuk.presentation.mealplan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makanyuk.domain.recipe.model.mealplan.DayMeal
import com.example.makanyuk.domain.recipe.usecase.network.RecipeUseCases
import com.example.makanyuk.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealPlanViewModel @Inject constructor(
    private val useCase : RecipeUseCases
) : ViewModel() {

    private var _dayMeal = MutableStateFlow<Resource<List<DayMeal>>>(Resource.Loading())
    val dayMeal : StateFlow<Resource<List<DayMeal>>> = _dayMeal.asStateFlow()

    init {
        getMealPlan()
    }

    private fun getMealPlan(){
        viewModelScope.launch {
            _dayMeal.value = Resource.Loading()
            useCase.getMealPlanUseCase().collect{
                _dayMeal.value = it
            }
        }
    }
}