package com.example.makanyuk.data.network.gemini

import android.content.Context
import android.net.Uri
import com.example.makanyuk.domain.ai.model.FoodAnalysis
import com.example.makanyuk.domain.ai.repo.AIRepository
import com.example.makanyuk.util.Resource
import com.example.makanyuk.util.Utility
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AiRepoImpl @Inject constructor(
    private val gemini: GenerativeModel
) : AIRepository {

    override suspend fun sendImage(context: Context, imgUri: Uri): Flow<Resource<FoodAnalysis>> {
        val prompt = """
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
        return flow {
            emit(Resource.Loading())
            try {
                val bitmap = Utility.uriToBitmap(context, imgUri)

                val response = gemini.generateContent(
                    content {
                        image(bitmap!!)
                        text(prompt)
                    }
                )
                response.text?.let {
                    val json = Utility.extractJsonSimple(response.text!!)
                    if (json.isNotEmpty()){
                        val res = Json.decodeFromString<FoodAnalysis>(json)
                        emit(Resource.Success(res))
                    } else{
                        emit(Resource.Error("Error"))
                    }
                }

            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }
}