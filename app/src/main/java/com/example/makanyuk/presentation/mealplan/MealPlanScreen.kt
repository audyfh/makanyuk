package com.example.makanyuk.presentation.mealplan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.makanyuk.presentation.comps.MealDayCard
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.util.Resource

@Composable
fun MealPlanScreen(
    modifier: Modifier = Modifier,
    viewModel: MealPlanViewModel = hiltViewModel(),
    navController: NavController
) {

    val dayMeal by viewModel.dayMeal.collectAsState()
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        Row (
            modifier = modifier.fillMaxWidth().padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text("Meal Plan", style = MaterialTheme.typography.titleMedium, color = Primary100)
        }
        Spacer(modifier.height(20.dp))

        when(dayMeal){
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Error -> {
                Text("Gagal memuat data")
            }
            is Resource.Success -> {
                LazyColumn(
                    modifier = modifier.fillMaxWidth()
                ) {
                    items(dayMeal.data?.size ?: 0){
                        MealDayCard(
                            day = dayMeal.data?.get(it)?.day ?: "",
                            listMeal = dayMeal.data?.get(it)?.meal!!,
                            nutrients = dayMeal.data?.get(it)?.nutrients!!,
                            navController = navController
                        )
                    }
                }
            }
        }

    }
}