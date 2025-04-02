package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.makanyuk.R
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.ui.theme.Primary100

@Composable
fun SavedCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            model = recipe.image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
        )
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.6f),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    recipe.title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    maxLines = 2,
                    color = Color.White
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                RatingCard(rating = recipe.spoonacularScore ?: 0.0)
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_timer),
                        contentDescription = ""
                    )
                    Text(
                        "${recipe.readyInMinutes} min",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Box(
                        modifier = modifier
                            .clip(CircleShape)
                            .background(color = Primary100)
                            .padding(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_saved),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = modifier.size(20.dp)
                        )
                    }
                }
            }
        }

    }
}