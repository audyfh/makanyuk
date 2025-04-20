package com.example.makanyuk.presentation.ai

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.makanyuk.R
import com.example.makanyuk.ui.theme.Gray4
import com.example.makanyuk.ui.theme.Primary100
import com.example.makanyuk.util.Resource

@Composable
fun TrackScreen(
    modifier: Modifier = Modifier,
    viewModel: AIViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val analysisState by viewModel.foodAnalysis.collectAsState()

    val imageuri = remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            imageuri.value = it
            viewModel.sendImage(context,it)
        }
    }

    val cameraImageUri = remember {
        mutableStateOf<Uri?>(null)
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && cameraImageUri.value != null) {
            imageuri.value = cameraImageUri.value
            viewModel.sendImage(context,imageuri.value!!)
        }

    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Calorie Tracker", style = MaterialTheme.typography.headlineMedium, color = Primary100)
        }
        Spacer(modifier.height(20.dp))
        UploadCard(
            imageUri = imageuri.value,
            onClick = {
                showImagePickerDialog(
                    context = context,
                    onSelectGallery = {galleryLauncher.launch("image/*")},
                    onSelectCamera = {
                        val uri = createImageUri(context)
                        cameraImageUri.value = uri
                        cameraLauncher.launch(uri)
                    }
                )
            }
        )
        Spacer(modifier.height(20.dp))
        when(analysisState){
            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is Resource.Error -> {
                val error = (analysisState as Resource.Error).msg
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("$error", style = MaterialTheme.typography.bodySmall)
                }
            }
            is Resource.Success -> {
                val data = (analysisState as Resource.Success).data

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text("Food Name: ${data?.foodName}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Calories: ${data?.calorie} kcal")

                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Macronutrients", style = MaterialTheme.typography.bodyLarge)
                    Text("• Carbs: ${data?.macronutrients?.carbs} g")
                    Text("• Protein: ${data?.macronutrients?.protein} g")
                    Text("• Fat: ${data?.macronutrients?.fat} g")
                    Text("• Fibre: ${data?.macronutrients?.fibre} g")

                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Ingredients", style = MaterialTheme.typography.bodyLarge)
                    data?.ingredients?.forEach { ingredient ->
                        Text("- $ingredient")
                    }
                }
            }
        }

    }

}

@Composable
fun UploadCard(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Gray4)
            .clickable { onClick() },
    ) {
        if (imageUri!=null){
            AsyncImage(
                model = imageUri,
                contentDescription = "makanan",
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(painter = painterResource(R.drawable.ic_camera), contentDescription = "")
                    Text("Upload foto makanan")
                }
            }
        }
    }
}

fun showImagePickerDialog(
    context: Context,
    onSelectGallery: () -> Unit,
    onSelectCamera: () -> Unit,
) {
   AlertDialog.Builder(context).apply {
       setTitle("Upload Photo")
       setItems(arrayOf("Kamera", "Galeri")) { _, which ->
           when (which) {
               0 -> onSelectCamera()
               1 -> onSelectGallery()
           }
       }
       setNegativeButton("Cancel", null)
   }.show()
    
}

fun createImageUri(context: Context): Uri {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "food_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }
    return context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    )!!
}