package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.makanyuk.ui.theme.Primary100

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    title : String,
    value : Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.bodyMedium)
        Text("$value", style = MaterialTheme.typography.bodyMedium, color = Primary100)
    }
}

data class InfoData(
    val title : String,
    val value : Int
)

