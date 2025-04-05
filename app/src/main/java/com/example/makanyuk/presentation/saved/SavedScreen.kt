package com.example.makanyuk.presentation.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.makanyuk.presentation.comps.SavedCard
import com.example.makanyuk.util.Resource

@Composable
fun SavedScren(
    modifier: Modifier = Modifier,
    viewModel: SavedViewModel = hiltViewModel(),
    navigateDetail : (Int) -> Unit
) {

    val recipes by viewModel.recipes.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp)
    ) {
        Spacer(modifier.height(12.dp))
        Row (
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text("Saved Recipes", style = MaterialTheme.typography.titleMedium)
        }
        Spacer(modifier.height(15.dp))
        LazyColumn (
            modifier = modifier.fillMaxSize()
        ){
            when(recipes){
                is Resource.Loading -> {
                    items(1){
                        CircularProgressIndicator()
                    }
                }
                is  Resource.Error -> {

                }
                is Resource.Success -> {
                    if (recipes.data?.size != 0) {
                        items(recipes.data?.size!!) {
                            SavedCard(
                                recipe = recipes.data!![it],
                                onClick = {
                                    navigateDetail(recipes.data!![it].id)
                                }
                            )
                            Spacer(modifier.height(8.dp))
                        }
                    } else {
                        items(1){
                            Text("No saved recipe", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}