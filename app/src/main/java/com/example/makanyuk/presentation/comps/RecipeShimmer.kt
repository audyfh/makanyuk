package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.makanyuk.R
import com.example.makanyuk.ui.theme.Gray3
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.ui.theme.Secondary20
import com.valentinilk.shimmer.shimmer

@Composable
fun RecipeShimmer(
    modifier: Modifier = Modifier.shimmer()
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.LightGray)
    ){
        Column(
            modifier = modifier.fillMaxSize()
        ) {  }
        Column(
            modifier = modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.LightGray)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
            Column {
                Text(
                    "Placeholder",
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
                            "Placeholder",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            color = Gray3
                        )
                    }
                    Box(
                        modifier = modifier
                            .clip(CircleShape)
                            .background(color = Color.LightGray)
                            .padding(4.dp)
                    ){

                    }
                }
            }

        }
    }
}