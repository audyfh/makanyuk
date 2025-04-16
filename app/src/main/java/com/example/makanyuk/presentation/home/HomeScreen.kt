package com.example.makanyuk.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.makanyuk.R
import com.example.makanyuk.presentation.auth.AuthViewModel
import com.example.makanyuk.presentation.comps.CategoryButton
import com.example.makanyuk.presentation.comps.CustomSearchField
import com.example.makanyuk.presentation.comps.RecipeCard
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.ui.theme.StarterProjectTheme
import com.example.makanyuk.util.Resource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateDetail : (Int) -> Unit,
    navigateSearch : (String) -> Unit
) {
    val account = authViewModel.accountState.collectAsState()
    val recipes by homeViewModel.recipes.collectAsState()
    val context = LocalContext.current

    val name = account.value.data?.name ?: "Guest"
    var query by remember { mutableStateOf("") }
    val category = listOf(
        "All", "Indian", "Italian", "Asian","Chinese"
    )
    var selected by remember { mutableStateOf(category[0]) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 24.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            }

    ) {
       Row(
           modifier = modifier
               .fillMaxWidth()
               .padding(horizontal = 24.dp, vertical = 12.dp),
           verticalAlignment = Alignment.CenterVertically,
       ){
           Column{
               Text(
                   "Hello ${name}!",
                   style = MaterialTheme.typography.titleMedium
               )
               Text(
                   "What are you cooking today?",
                   style = MaterialTheme.typography.labelMedium
               )
           }
       }
        Spacer(modifier.height(20.dp))
       Row (
           modifier = modifier
               .fillMaxWidth()
               .padding(horizontal = 24.dp),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
       ){
           CustomSearchField(
               modifier = modifier.width(250.dp),
               placeholder = "Search recipe",
               input = query,
               onValueChange = {query = it},
               onSearch = {navigateSearch(query)}
           )
          Box{
              Column (
                  modifier = modifier
                      .clip(RoundedCornerShape(12.dp))
                      .background(color = Primary100)
              ){
                  Icon(
                      painter = painterResource(R.drawable.ic_filter),
                      contentDescription = "",
                      tint = Color.White,
                      modifier = modifier.padding(12.dp)
                  )
              }

          }

       }
        Spacer(modifier.height(15.dp))
        LazyRow (
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        ){
            items(category.size){
                CategoryButton(
                    category = category[it],
                    isSelected =  category[it] == selected ,
                    onClick = {
                        selected = category[it]
                    }
                )
            }
        }
        LazyVerticalGrid(
            modifier = modifier.padding(12.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when(recipes){
                is Resource.Loading -> {
                    items(1){
                        CircularProgressIndicator()
                    }
                }
                is Resource.Success -> {
                    items(recipes.data?.size ?: 0){ recipe ->
                        RecipeCard(
                            recipe = recipes.data?.get(recipe)!!,
                            onClick = {
                                val id = recipes.data!![recipe].id
                                Log.d("DetailScreen", "Navigasi ke detail dengan ID: $id")
                                navigateDetail(id)
                            }
                        )
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        recipes.msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}



