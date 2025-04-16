package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.makanyuk.ui.theme.Gray3
import com.example.makanyuk.ui.theme.Gray4
import com.example.makanyuk.ui.theme.Primary60
import com.valentinilk.shimmer.shimmer

@Composable
fun MealCardShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().shimmer()
    ) {
        Box (
            modifier = modifier.width(50.dp).background(Gray4)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(12.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Gray3)
        ){
            Box(
                modifier = modifier
                    .padding(start = 5.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Gray4)
            )
            Spacer(modifier.width(14.dp))
            Column(
                modifier = modifier
                    .fillMaxSize(0.8f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceAround
            ){
                Box (
                    modifier = modifier.width(70.dp).background(Gray4)
                )
                Box (
                    modifier = modifier.width(50.dp).background(Gray4)
                )
            }
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp)
                .background(color = Gray4)
        )
    }

}