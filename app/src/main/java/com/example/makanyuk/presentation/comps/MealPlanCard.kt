package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.makanyuk.domain.mealplan.Meal
import com.example.makanyuk.presentation.navigation.DetailRoute
import com.example.makanyuk.ui.theme.Gray4
import com.example.makanyuk.ui.theme.Primary60
import com.example.makanyuk.ui.theme.StarterProjectTheme

@Composable
fun MealPlanCard(
    modifier: Modifier = Modifier,
    meal: Meal,
    navController: NavController
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Primary60)
            .clickable {
               navController.navigate(DetailRoute(
                   id = meal.id
               ))
            },
        verticalAlignment = Alignment.CenterVertically,

        ) {

        AsyncImage(
            model = "https://img.spoonacular.com/recipes/${meal.id}-90x90.${meal.imageType}",
            contentDescription = "recipe",
            modifier = modifier
                .padding(start = 5.dp)
                .size(80.dp)
                .clip(RoundedCornerShape(14.dp))

        )
        Spacer(modifier.width(14.dp))
        Column(
            modifier = modifier
                .fillMaxSize(0.8f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                meal.title,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                maxLines = 2,
                color = White
            )
            Row {
                Text("${meal.readyInMinutes} min •", color = White, style = MaterialTheme.typography.labelMedium)
                Spacer(modifier.width(5.dp))
                Text("${meal.servings} servings", color = White, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

