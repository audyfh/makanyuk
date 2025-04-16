package com.example.makanyuk.presentation.ai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.makanyuk.presentation.comps.AiMenuCard
import com.example.makanyuk.presentation.navigation.TrackRoute
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.ui.theme.StarterProjectTheme

@Composable
fun AiScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth().padding(top = 12.dp)
        ) {
            Text("Smart AI", style = MaterialTheme.typography.headlineMedium, color = Primary100)
        }
        Spacer(
            modifier.height(20.dp)
        )
        AiMenuCard(
            title = "Calorie Tracker",
            desc = "Track your calorie with our Smart AI"
        ) { navController.navigate(TrackRoute) }
        Spacer(
            modifier.height(10.dp)
        )
        AiMenuCard(
            title = "Chat",
            desc = "Get unique recommendation"
        ) { }
        Spacer(
            modifier.height(10.dp)
        )
        AiMenuCard(
            title = "Personal Recipe",
            desc = "Make your personalize recipe"
        ) { }
        Spacer(
            modifier.height(10.dp)
        )
        AiMenuCard(
            title = "Cooking Assistant",
            desc = "Your smart assistant"
        ) { }

    }
}
