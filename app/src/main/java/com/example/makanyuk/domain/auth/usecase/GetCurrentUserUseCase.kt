package com.example.makanyuk.domain.auth.usecase

import com.example.makanyuk.domain.auth.repo.AuthRepo
import com.example.makanyuk.util.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

class GetCurrentUserUseCase(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke() : Flow<Resource<FirebaseUser>> = authRepo.getCurrentUser()
}