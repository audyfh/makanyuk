package com.example.makanyuk.domain.auth.usecase

data class AuthUseCase(
    val login : LoginUseCase,
    val register : RegisterUseCase,
    val logout : LogoutUseCase,
    val forgotPassword : ForgotPasswordUseCase,
    val getCurrentUser : GetCurrentUserUseCase,
    val getUserProfile : GetUserProfileUseCase
)