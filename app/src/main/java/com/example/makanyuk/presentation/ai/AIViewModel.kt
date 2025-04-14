package com.example.makanyuk.presentation.ai

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makanyuk.domain.ai.model.FoodAnalysis
import com.example.makanyuk.domain.ai.repo.AIRepository
import com.example.makanyuk.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AIViewModel @Inject constructor(
    private val repo: AIRepository
) : ViewModel() {

    private var _foodAnalysis = MutableStateFlow<Resource<FoodAnalysis>>(Resource.Error("Upload Photo"))
    val foodAnalysis: StateFlow<Resource<FoodAnalysis>> = _foodAnalysis.asStateFlow()


    fun sendImage(
        context: Context,
        imgUri: Uri
    ) {
        viewModelScope.launch {
            _foodAnalysis.value = Resource.Loading()
            repo.sendImage(context,imgUri).collectLatest {
                _foodAnalysis.value = it
            }
        }

    }
}

