package com.example.makanyuk.model.auth.repo

import com.example.makanyuk.model.auth.Account
import com.example.makanyuk.util.Resource
import kotlinx.coroutines.flow.Flow

interface AccountRepo {
    suspend fun getUserProfile() : Resource<Account>
}