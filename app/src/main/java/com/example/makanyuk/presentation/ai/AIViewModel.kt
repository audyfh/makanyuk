package com.example.makanyuk.presentation.ai

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makanyuk.domain.ai.model.FoodAnalysis
import com.example.makanyuk.util.Resource
import com.example.makanyuk.util.Utility
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class AIViewModel @Inject constructor(
    private val gemini: GenerativeModel
) : ViewModel() {

    private var _foodAnalysis = MutableStateFlow<Resource<FoodAnalysis>>(Resource.Loading())
    val foodAnalysis : StateFlow<Resource<FoodAnalysis>> = _foodAnalysis.asStateFlow()

    private var _responseAi = MutableStateFlow<String>("")
    val responseAI: StateFlow<String> = _responseAi.asStateFlow()

    val prompt ="""
Analyze the food in the image and return only the JSON below (no extra text, no ```json):

{
  "foodName": string,
  "calorie": number,
  "macronutrients": {
    "carbs": number,
    "protein": number,
    "fat": number,
    "fibre": number
  },
  "ingredients": [string]
}
""".trimIndent()
    val simplePrompt =
        "Berikan analisis makanan pada gambar ini. Tampilkan nama makanan, estimasi kalori, tidak apa-apa kalorinya tidak akurat yang penting ada. Berikan juga bahan penyusun makananna"

    fun sendImage(
        context: Context,
        imgUri: Uri
    ) {
        viewModelScope.launch {
            try {
                _foodAnalysis.value = Resource.Loading()
                val bitmap = uriToBitmap(context, imgUri)

                val response = gemini.generateContent(
                    content {
                        image(bitmap!!)
                        text(prompt)
                    }
                )
                response.text?.let {
                    Log.d("GeminiResponse", response.text!!)
                    _responseAi.value = response.text!!
                    val jsonText = Utility.extractJsonSimple(response.text!!)
                    if(jsonText!=null){
                        val res = Json.decodeFromString<FoodAnalysis>(jsonText)
                        _foodAnalysis.value = Resource.Success(res)
                    } else {
                        _foodAnalysis.value = Resource.Error("error")
                    }
                }
            } catch (e: Exception) {
                _foodAnalysis.value = Resource.Error(e.localizedMessage)
            }

        }

    }
}

private fun uriToBitmap(
    context: Context,
    uri: Uri
): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}