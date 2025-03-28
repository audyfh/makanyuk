package com.example.makanyuk.presentation.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.makanyuk.model.auth.Account
import com.example.makanyuk.model.auth.repo.AccountRepo
import com.example.makanyuk.model.auth.repo.AuthRepo
import com.example.makanyuk.presentation.navigation.Route
import com.example.makanyuk.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepo,
    private val accountRepo: AccountRepo
) : ViewModel() {

    private var _authState = MutableStateFlow<Resource<AuthResult>>(Resource.Loading())
    val authState: StateFlow<Resource<AuthResult>> = _authState.asStateFlow()

    private var _loginState = MutableStateFlow<Resource<FirebaseUser>>(Resource.Loading())
    val loginState: StateFlow<Resource<FirebaseUser>> = _loginState.asStateFlow()

    private var _accountState = MutableStateFlow<Resource<Account>>(Resource.Loading())
    val accountState: StateFlow<Resource<Account>> = _accountState.asStateFlow()

    var startDestination = Route.LoginScreen.route

    init {
        getCurrentUser()
        getUserData()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepo.loginEmail(email, password).collect {
                _authState.value = it

            }
            getUserData()
        }
    }

    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                _authState.value = Resource.Error("Ada data yang kosong")
                return@launch
            }

            if (password != confirmPassword) {
                _authState.value = Resource.Error("Ada data yang kosong")
                return@launch
            }
            authRepo.registerEmail(
                name = name,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            ).collect {
                _authState.value = it
            }
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            authRepo.getCurrentUser().collect { result ->
                _loginState.value = result
                if (result is Resource.Success){
                    startDestination = Route.MainNavigation.route
                }

            }
        }
    }

    private fun getUserData() {
        viewModelScope.launch {
           val data  = accountRepo.getUserProfile()
            _accountState.value = data
        }
    }
}