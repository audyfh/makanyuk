package com.example.makanyuk.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
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
import com.example.makanyuk.presentation.navigation.LoginRoute
import com.example.makanyuk.ui.theme.Gray4
import com.example.makanyuk.ui.theme.Secondary100
import com.example.makanyuk.ui.theme.StarterProjectTheme
import com.example.makanyuk.util.Resource

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val authState by viewModel.registerState.collectAsState()
    LaunchedEffect(authState) {
        when(authState){
            is Resource.Loading -> {}
            is Resource.Error -> Toast.makeText(context,authState.msg,Toast.LENGTH_SHORT).show()
            is Resource.Success -> navController.navigate(LoginRoute)
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            Spacer(modifier.height(15.dp))
            Text("Create an Account", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier.height(5.dp))
            Text(
                "Let’s help you set up your account,\n it won’t take long.",
                style = MaterialTheme.typography.labelMedium
            )

        }
        Column(

        ) {
            Text("Name")
            Spacer(modifier.height(5.dp))
            AuthTextField(placeholder = "Name", input = name ) {
                name = it
            }
            Spacer(modifier.height(15.dp))
            Text("Email")
            Spacer(modifier.height(5.dp))
            AuthTextField(placeholder = "Email", input = email) {
                email = it
            }
            Spacer(modifier.height(15.dp))
            Text("Password")
            Spacer(modifier.height(5.dp))
            AuthTextField(placeholder = "Password", input = password) {
                password = it
            }
            Spacer(modifier.height(15.dp))
            Text("Confirm Password")
            Spacer(modifier.height(5.dp))
            AuthTextField(placeholder = "Retype password", input = confirmPassword) {
                confirmPassword = it
            }

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                    },
                    colors = CheckboxDefaults.colors(
                        disabledUncheckedColor = Secondary100,
                        uncheckedColor = Secondary100
                    )
                )
                Text(
                    "Accept terms & Condition",
                    style = MaterialTheme.typography.labelMedium,
                    color = Secondary100
                )
            }
            Spacer(modifier.height(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                ArrowButton(text = "Sign Up") {
                    viewModel.register(
                        name.trim(),email.trim(),password.trim(),confirmPassword.trim()
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
        Row (
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text("Already a member? ", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold))
            Text("Sign In",style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold), color = Secondary100)
        }
    }

}

