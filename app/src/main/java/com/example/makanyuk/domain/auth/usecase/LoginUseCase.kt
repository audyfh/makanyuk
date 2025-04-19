package com.example.makanyuk.domain.auth.usecase

import com.example.makanyuk.domain.auth.repo.AuthRepo
import com.example.makanyuk.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke(
        email : String, password: String
    ) : Flow<Resource<AuthResult>> = authRepo.loginEmail(email,password)
}