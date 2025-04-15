package com.example.makanyuk.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.makanyuk.R
import com.example.makanyuk.presentation.auth.AuthViewModel
import com.example.makanyuk.presentation.comps.InfoCard
import com.example.makanyuk.presentation.comps.InfoData
import com.example.makanyuk.ui.theme.Gray2
import com.example.makanyuk.ui.theme.Gray3
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.ui.theme.Primary20
import com.example.makanyuk.ui.theme.StarterProjectTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    navigateLogin : () -> Unit
) {
    val account = authViewModel.accountState.collectAsState()
    val name = account.value.data?.name ?: "Guest"
    val email = account.value.data?.email ?: "guest@gmail.com"

    val listInfoData = listOf(
        InfoData("Current Weight", 80),
        InfoData("Target Weight", 70),
        InfoData("Height", 70),
        InfoData("BMI Index", 5),
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Primary20)
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Profile", style = MaterialTheme.typography.headlineMedium, color = Primary100)
        }
        Spacer(modifier.height(12.dp))
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.profilepic),
                contentDescription = null,
                modifier = modifier.size(100.dp)
            )
            Spacer(modifier.height(18.dp))
            Text(name, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier.height(5.dp))
            Text(email, style = MaterialTheme.typography.bodyMedium, color = Gray3)
        }
        Text("Info", style = MaterialTheme.typography.bodySmall, color = Gray2)
        Spacer(modifier.height(5.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White)
                .padding(bottom = 2.dp)

        ) {
            listInfoData.forEach {
                InfoCard(title = it.title, value = it.value)
                HorizontalDivider()
            }

        }
        Spacer(modifier.height(12.dp))
        Text("Info", style = MaterialTheme.typography.bodySmall, color = Gray2)
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White)
        ) {
            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Settings",style = MaterialTheme.typography.bodyMedium)
                Icon(Icons.AutoMirrored.Default.ArrowForward, contentDescription = "")
            }
            HorizontalDivider()
            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clickable {
                        authViewModel.logout()
                        navigateLogin()
                    }
            ){
                Text("Logout", style = MaterialTheme.typography.bodyMedium, color = Color.Red )
            }
            HorizontalDivider()

        }

    }
}



