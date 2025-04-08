package com.example.makanyuk.presentation.ai

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.makanyuk.R
import com.example.makanyuk.ui.theme.Gray4
import com.example.makanyuk.ui.theme.Primary100

@Composable
fun TrackScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val imageuri = remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { imageuri.value = it }
    }

    val cameraImageUri = remember {
        mutableStateOf<Uri?>(null)
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            cameraImageUri.value = cameraImageUri.value
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
            .height(150.dp)
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