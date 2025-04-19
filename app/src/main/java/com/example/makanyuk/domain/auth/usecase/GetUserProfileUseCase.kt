package com.example.makanyuk.domain.auth.usecase

import com.example.makanyuk.domain.auth.Account
import com.example.makanyuk.domain.auth.repo.AccountRepo
import com.example.makanyuk.util.Resource

class GetUserProfileUseCase(
    private val accountRepo: AccountRepo
) {
    suspend operator fun invoke () : Resource<Account> = accountRepo.getUserProfile()
}