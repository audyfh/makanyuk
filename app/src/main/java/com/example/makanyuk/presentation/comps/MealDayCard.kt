package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.makanyuk.domain.mealplan.Meal
import com.example.makanyuk.domain.mealplan.Nutrients
import com.example.makanyuk.ui.theme.Primary80
import com.example.makanyuk.ui.theme.Secondary100
import com.example.makanyuk.ui.theme.Secondary60
import kotlin.math.roundToInt

@Composable
fun MealDayCard(
    modifier: Modifier = Modifier,
    day: String,
    listMeal: List<Meal>,
    nutrients: Nutrients,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(day, style = MaterialTheme.typography.titleSmall)
        Column {
            listMeal.forEachIndexed{ index, meal ->
                androidx.compose.runtime.key(meal.id ?: index) {
                    MealPlanCard(meal = meal, navController = navController)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
//        listMeal.forEach { meal ->
//            MealPlanCard(meal = meal, navController = navController)
//            Spacer(modifier = Modifier.height(8.dp))
//        }
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
                Text(
                    "Total nutritions",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Secondary60
                )
            }
            Spacer(modifier.height(8.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(0.5f)

                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            "calories : ",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        Text(
                            "${nutrients.calories.roundToInt()} g",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                    Spacer(modifier.height(5.dp))
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            "protein   : ",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        Text(
                            "${nutrients.protein.roundToInt()} g",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }

                }
                Column(
                    modifier = modifier.fillMaxWidth(0.6f)
                ) {
                    Row (
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            "fat        : ",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        Text(
                            "${nutrients.fat.roundToInt()} g",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                    Spacer(modifier.height(5.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "carbs : ",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        Text(
                            "${nutrients.carbohydrates.roundToInt()} g",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}