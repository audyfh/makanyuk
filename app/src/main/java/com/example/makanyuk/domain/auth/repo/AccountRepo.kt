package com.example.makanyuk.domain.auth.repo

import com.example.makanyuk.domain.auth.Account
import com.example.makanyuk.util.Resource

interface AccountRepo {
    suspend fun getUserProfile() : Resource<Account>
}