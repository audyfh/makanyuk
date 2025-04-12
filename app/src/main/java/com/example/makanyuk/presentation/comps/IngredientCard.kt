package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.makanyuk.ui.theme.Gray4
import com.example.makanyuk.ui.theme.Primary60
import org.jetbrains.annotations.Async

@Composable
fun IngredientCard(
    modifier: Modifier = Modifier,
    imgUrl: String,
    title:String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Primary60)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = imgUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier.fillMaxHeight().width(70.dp).clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = modifier.width(10.dp))
        Text(title, style = MaterialTheme.typography.bodyMedium, color = Color.White)
    }

}