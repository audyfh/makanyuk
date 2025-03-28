package com.example.makanyuk.presentation.comps

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.makanyuk.ui.theme.Primary100

@Composable
fun NormalButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick : () -> Unit
) {
    Button(
        onClick,
        colors = ButtonColors(
            containerColor = Primary100,
            contentColor = Color.White,
            disabledContainerColor = Primary100,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp)

    ) {
        Text(
            text,
            style = MaterialTheme.typography.titleMedium

        )
    }
}