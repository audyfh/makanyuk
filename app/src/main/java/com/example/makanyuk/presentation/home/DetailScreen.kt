package com.example.makanyuk.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.makanyuk.R
import com.example.makanyuk.domain.recipe.Recipe
import com.example.makanyuk.presentation.comps.IngredientCard
import com.example.makanyuk.presentation.comps.InstructionCard
import com.example.makanyuk.presentation.comps.RatingCard
import com.example.makanyuk.ui.theme.Gray3
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.util.Resource

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    id : Int
) {
    LaunchedEffect(id) {
        viewModel.getDetailRecipe(id)
    }

    val recipe by viewModel.singleRecipe.collectAsState()
    var btnState by remember { mutableIntStateOf(0) }
    val ingredients = recipe.data?.extendedIngredients
    val instruction = recipe.data?.analyzedInstructions
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when(recipe){
            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = modifier.fillMaxSize(0.8f)
                )
            }
            is Resource.Error -> {
                Toast.makeText(context,recipe.msg,Toast.LENGTH_SHORT).show()
            }
            is Resource.Success -> {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "arrow")
                    Icon(Icons.Default.MoreVert, contentDescription = "")
                }
                Box(
                    modifier = modifier.fillMaxWidth().height(200.dp)
                ) {
                    AsyncImage(
                        model = recipe.data?.image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = modifier.fillMaxSize()
                    )
                    Column(
                        modifier = modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        RatingCard(rating = recipe.data?.spoonacularScore ?: 0.0)
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Icon(painter = painterResource(R.drawable.ic_timer), contentDescription = "")
                            Text(
                                "${recipe.data?.readyInMinutes} min",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White
                            )
                            Box(
                                modifier = modifier
                                    .clip(CircleShape)
                                    .background(color = Primary100)
                                    .padding(4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_saved),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
                Text(
                    recipe.data?.title ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold),
                    overflow = TextOverflow.Clip,
                    maxLines = 2
                )
                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            btnState = 0
                        },
                        colors = ButtonColors(
                            containerColor = if (btnState == 0) Primary100 else Color.White,
                            contentColor = if (btnState == 0) Color.White else Primary100,
                            disabledContainerColor = Primary100,
                            disabledContentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = modifier
                            .fillMaxWidth()
                            .height(30.dp)

                    ) {
                        Text(
                            "Ingredient",
                            style = MaterialTheme.typography.titleMedium

                        )
                    }
                    Button(
                        onClick = {
                            btnState = 1
                        },
                        colors = ButtonColors(
                            containerColor = if (btnState == 1) Primary100 else Color.White,
                            contentColor = if (btnState == 1) Color.White else Primary100,
                            disabledContainerColor = Primary100,
                            disabledContentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = modifier
                            .fillMaxWidth()
                            .height(30.dp)

                    ) {
                        Text(
                            "Procedure",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_serve),
                        contentDescription = "",
                        tint = Gray3
                    )
                    Text(
                        recipe.data?.servings.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        color = Gray3
                    )
                }
                LazyColumn(

                ) {
                    when (btnState) {
                        0 -> {
                            items(ingredients?.size!!) {
                                IngredientCard(
                                    imgUrl = "https://img.spoonacular.com/ingredients_100x100/${
                                        ingredients[it].image
                                    }",
                                    title = ingredients[it].name
                                )
                            }

                        }
                        1 -> {
                            if (instruction!!.isNotEmpty()){
                                items(instruction[0].steps.size){
                                    InstructionCard(
                                        step = instruction[0].steps[it].number,
                                        instruction = instruction[0].steps[it].step
                                    )
                                }
                            } else {
                                items(1){
                                    InstructionCard(
                                        step = 1,
                                        instruction = recipe.data?.instructions!!
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}