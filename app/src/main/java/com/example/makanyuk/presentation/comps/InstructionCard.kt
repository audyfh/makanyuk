package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.makanyuk.ui.theme.Gray3
import com.example.makanyuk.ui.theme.Gray4

@Composable
fun InstructionCard(
    modifier: Modifier = Modifier,
    instruction: String,
    step: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Gray4)
    ) {
        Column {
            Text("Step $step", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier.height(10.dp))
            Text(instruction, style = MaterialTheme.typography.bodySmall, color = Gray3)
        }
    }
}