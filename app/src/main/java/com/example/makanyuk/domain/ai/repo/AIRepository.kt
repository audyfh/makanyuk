package com.example.makanyuk.domain.ai.repo

import android.content.Context
import android.net.Uri
import com.example.makanyuk.domain.ai.model.FoodAnalysis
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

interface AIRepository {
    suspend fun sendImage(context: Context,
                          imgUri: Uri
    ) : Flow<Resource<FoodAnalysis>>
}