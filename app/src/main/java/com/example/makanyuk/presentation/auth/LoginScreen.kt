package com.example.makanyuk.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.makanyuk.R
import com.example.makanyuk.presentation.comps.ArrowButton
import com.example.makanyuk.presentation.comps.AuthTextField
import com.example.makanyuk.presentation.navigation.MainRoute
import com.example.makanyuk.presentation.navigation.Route
import com.example.makanyuk.ui.theme.Gray4
import com.example.makanyuk.ui.theme.Secondary100
import com.example.makanyuk.ui.theme.StarterProjectTheme
import com.example.makanyuk.util.Resource

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by viewModel.authState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(authState) {
        when(authState){
            is Resource.Loading -> {}
            is Resource.Error -> Toast.makeText(context,(
                    authState as Resource.Error
                    ).msg ?: "Gagal Login", Toast.LENGTH_SHORT).show()
            is Resource.Success -> navController.navigate(MainRoute){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.toque_svg),
            contentDescription = "",
            colorFilter = ColorFilter.tint(
                Color.Black
            )
        )
        Column() {
            Text("Email", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier.height(5.dp))
            AuthTextField(placeholder = "Email", input = email) {
                email = it
            }
            Spacer(modifier.height(15.dp))
            Text("Enter Password", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier.height(5.dp))
            AuthTextField(placeholder = "Enter Password", input = password) {
                password = it
            }
            Spacer(modifier.height(20.dp))
            Text(
                "Forgot Password?",
                style = MaterialTheme.typography.labelMedium,
                color = Secondary100
            )
            Spacer(modifier.height(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                ArrowButton(text = "Sign In") {
                    viewModel.login(
                        email, password
                    )
                }
                Spacer(modifier.height(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    HorizontalDivider(modifier = modifier.width(48.dp))
                    Spacer(modifier.width(8.dp))
                    Text("Or Sign in With", style = MaterialTheme.typography.labelMedium, color = Gray4)
                    Spacer(modifier.width(8.dp))
                    HorizontalDivider(modifier = modifier.width(48.dp))

                }
                Spacer(modifier.height(20.dp))
                Row{
                    Image(
                        painter = painterResource(R.drawable.btn_google),
                        contentDescription = "google",
                        modifier = modifier.size(80.dp)
                    )
                    Spacer(modifier.width(10.dp))
                    Image(
                        painter = painterResource(R.drawable.btn_facebook),
                        contentDescription = "facebook",
                        modifier = modifier.size(80.dp)
                    )
                }
            }
           
        }
        Row(
            modifier.clickable {
                navController.navigate(Route.RegisterScreen.route)
            }
        ) {
            Text("Dont have an Account? ", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold))
            Text("Sign Up",style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold), color = Secondary100)
        }


    }
}

