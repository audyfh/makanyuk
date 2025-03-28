package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.makanyuk.R
import com.example.makanyuk.ui.theme.Rating
import com.example.makanyuk.ui.theme.Secondary20
import kotlin.math.roundToInt

@Composable
fun RatingCard(
    modifier: Modifier = Modifier,
    rating : Double
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Secondary20)
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_rating),
                contentDescription = "rating",
                tint = Rating
            )
            Spacer(modifier.width(5.dp))
            Text(
                rating.roundToInt().toString(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}