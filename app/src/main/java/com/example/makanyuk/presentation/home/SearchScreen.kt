package com.example.makanyuk.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.makanyuk.presentation.comps.RecipeShimmer
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.util.Resource
import com.valentinilk.shimmer.shimmer

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    query: String,
    onClick: () -> Unit
) {
    val state by viewModel.searchResult.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.searchRecipe(query)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                "Search Result",
                style = MaterialTheme.typography.headlineMedium,
                color = Primary100
            )
        }
        when (state) {
            is Resource.Loading -> {
                LazyVerticalGrid(
                    modifier = modifier.padding(12.dp).shimmer(),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(8){
                        RecipeShimmer()
                    }
                }
            }

            is Resource.Error -> {
                Text("${state.msg}")
            }

            is Resource.Success -> {
                LazyVerticalGrid(
                    modifier = modifier.padding(12.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.data?.size ?: 0) {
                        Box(
                            modifier = modifier
                                .height(170.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    onClick()
                                }
                        ) {
                            AsyncImage(
                                model = state.data?.get(it)?.image,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = modifier.fillMaxSize()
                            )
                            Column(
                                modifier = modifier.fillMaxSize().padding(12.dp),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    state.data?.get(it)?.title ?: "",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold),
                                    color = Color.White,
                                    overflow = TextOverflow.Clip,
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}