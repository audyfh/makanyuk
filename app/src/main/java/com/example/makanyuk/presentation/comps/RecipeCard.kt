package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.makanyuk.R
import com.example.makanyuk.domain.recipe.model.Recipe
import com.example.makanyuk.ui.theme.Gray3
import com.example.makanyuk.ui.theme.Primary100

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onClick : () -> Unit
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable{
                onClick()
            }
    ) {
        AsyncImage(
            model = recipe.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
        Column(
            modifier = modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                RatingCard(
                    rating = recipe.spoonacularScore
                )
            }
            Column {
                Text(
                    recipe.title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.White,
                    overflow = TextOverflow.Clip,
                    maxLines = 2
                )
                Row (
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column {
                        Text(
                            "Time",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = Gray3
                        )
                        Text(
                            recipe.readyInMinutes.toString(),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = modifier
                            .clip(CircleShape)
                            .background(color = Primary100)
                            .padding(4.dp)
                    ){
                        Icon(painter = painterResource(R.drawable.ic_saved), contentDescription = null, tint = Color.White)
                    }
                }
            }
        }
    }
}