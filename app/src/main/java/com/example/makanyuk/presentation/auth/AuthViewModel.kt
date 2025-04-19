package com.example.makanyuk.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makanyuk.domain.auth.Account
import com.example.makanyuk.domain.auth.repo.AccountRepo
import com.example.makanyuk.domain.auth.repo.AuthRepo
import com.example.makanyuk.domain.auth.usecase.AuthUseCase
import com.example.makanyuk.presentation.navigation.AppRoute
import com.example.makanyuk.presentation.navigation.LoginRoute
import com.example.makanyuk.presentation.navigation.MainRoute
import com.example.makanyuk.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private var _authState = MutableStateFlow<Resource<AuthResult>>(Resource.Loading())
    val authState: StateFlow<Resource<AuthResult>> = _authState.asStateFlow()

    private var _registerState = MutableStateFlow<Resource<AuthResult>>(Resource.Loading())
    val registerState : StateFlow<Resource<AuthResult>> = _registerState.asStateFlow()

    private var _loginState = MutableStateFlow<Resource<FirebaseUser>>(Resource.Loading())
    val loginState: StateFlow<Resource<FirebaseUser>> = _loginState.asStateFlow()

    private var _accountState = MutableStateFlow<Resource<Account>>(Resource.Loading())
    val accountState: StateFlow<Resource<Account>> = _accountState.asStateFlow()

    var startDestination : AppRoute = LoginRoute

    init {
        getCurrentUser()
        getUserData()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(email, password).collect {
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
           authUseCase.register(name, email, password, confirmPassword).collect{
               _registerState.value = it
           }
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            authUseCase.getCurrentUser().collect { result ->
                _loginState.value = result
                if (result is Resource.Success){
                    startDestination = MainRoute
                }

            }
        }
    }

    private fun getUserData() {
        viewModelScope.launch {
           val data  = authUseCase.getUserProfile()
            _accountState.value = data
        }
    }

    fun logout(){
        viewModelScope.launch {
            authUseCase.logout()
        }
    }
}