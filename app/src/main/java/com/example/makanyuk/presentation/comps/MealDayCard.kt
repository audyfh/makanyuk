package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.makanyuk.domain.mealplan.Meal
import com.example.makanyuk.domain.mealplan.Nutrients
import com.example.makanyuk.ui.theme.Primary80

@Composable
fun MealDayCard(
    modifier: Modifier = Modifier,
    day: String,
    listMeal: List<Meal>,
    nutrients: Nutrients
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(day, style = MaterialTheme.typography.titleSmall)
        listMeal.forEach { meal ->
            MealPlanCard(meal = meal)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Primary80),
            verticalArrangement = Arrangement.SpaceAround

        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Total nutritions", style = MaterialTheme.typography.bodyMedium, color = Color.White)
            }
            Spacer(modifier.height(8.dp))
            Row(
                modifier = modifier.fillMaxWidth().padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("calories : ${nutrients.calories} ", style = MaterialTheme.typography.bodySmall, color = Color.White )
                    Spacer(modifier.height(5.dp))
                    Text("protein : ${nutrients.protein}",style = MaterialTheme.typography.bodySmall, color = Color.White)
                }
                Column {
                    Text("fat : ${nutrients.fat}",style = MaterialTheme.typography.bodySmall, color = Color.White)
                    Spacer(modifier.height(5.dp))
                    Text("carbs : ${nutrients.carbohydrates}",style = MaterialTheme.typography.bodySmall, color = Color.White)
                }
            }
        }
    }
}