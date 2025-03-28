package com.example.makanyuk.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.makanyuk.R
import com.example.makanyuk.presentation.comps.ArrowButton
import com.example.makanyuk.ui.theme.StarterProjectTheme

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.bg_onboarding),
            contentDescription = "background",
            modifier = modifier.fillMaxSize()
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.drawable.toque_svg), contentDescription = "")
                Spacer(modifier.height(10.dp))
                Text("100K+ Premium Recipe", color = Color.White)
            }
            Text("Masak Yuk", style = MaterialTheme.typography.headlineLarge, color = Color.White)
            ArrowButton(text = "Start Cooking", modifier = modifier.padding(bottom = 18.dp)) { }

        }
    }
}

@Preview
@Composable
private fun prevOnBoard() {
    StarterProjectTheme {
        OnBoardingScreen()
    }
}