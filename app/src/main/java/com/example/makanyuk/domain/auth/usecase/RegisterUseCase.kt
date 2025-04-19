package com.example.makanyuk.domain.auth.usecase

import com.example.makanyuk.domain.auth.repo.AuthRepo
import com.example.makanyuk.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class RegisterUseCase(
    private val authRepo: AuthRepo
) {
    operator fun invoke(
        name: String, email : String, password: String, confirmPassword : String
    ) : Flow<Resource<AuthResult>> = flow {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            emit(Resource.Error("Ada data yang kosong"))
            return@flow
        }

        if (password != confirmPassword) {
            emit(Resource.Error("Password berbeda"))
            return@flow
        }

        emitAll(authRepo.registerEmail(name, email, password, confirmPassword))
    }
}