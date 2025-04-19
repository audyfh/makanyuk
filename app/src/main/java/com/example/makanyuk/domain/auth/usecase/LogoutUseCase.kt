package com.example.makanyuk.domain.auth.usecase

import com.example.makanyuk.domain.auth.repo.AuthRepo
import com.example.makanyuk.util.Resource

class LogoutUseCase(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke() : Resource<Unit> = authRepo.logout()
}