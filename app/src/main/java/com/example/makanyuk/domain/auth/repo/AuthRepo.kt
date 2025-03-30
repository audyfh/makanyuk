package com.example.makanyuk.domain.auth.repo

import com.example.makanyuk.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepo {
    suspend fun loginEmail (email:String, password:String) : Flow<Resource<AuthResult>>
    suspend fun registerEmail (
        name : String,
        email: String,
        password: String,
        confirmPassword: String
    ) : Flow<Resource<AuthResult>>
    suspend fun forgotPassword (email: String) : Resource<Unit>
    suspend fun createAccount(id:String, name:String, email: String) : Resource<Unit>
    suspend fun getCurrentUser() : Flow<Resource<FirebaseUser>>
}