package com.example.makanyuk.domain.auth.usecase

import com.example.makanyuk.domain.auth.repo.AuthRepo
import com.example.makanyuk.util.Resource

class ForgotPasswordUseCase(
    private val authRepo : AuthRepo
) {
    suspend operator fun invoke(
        email : String
    ) : Resource<Unit> =  authRepo.forgotPassword(email)
}