package com.example.makanyuk.di

import com.example.makanyuk.data.local.RoomRecipeRepoImpl
import com.example.makanyuk.data.network.firebase.auth.AccountRepoImpl
import com.example.makanyuk.data.network.firebase.auth.AuthRepoImpl
import com.example.makanyuk.data.network.gemini.AiRepoImpl
import com.example.makanyuk.data.network.retrofit.repository.RecipeRepoImpl
import com.example.makanyuk.domain.ai.repo.AIRepository
import com.example.makanyuk.domain.auth.repo.AccountRepo
import com.example.makanyuk.domain.auth.repo.AuthRepo
import com.example.makanyuk.domain.recipe.repo.RecipeRepo
import com.example.makanyuk.domain.recipe.repo.RoomRecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun provideRecipeRepo(
        recipeRepoImpl: RecipeRepoImpl
    ) : RecipeRepo

    @Binds
    @Singleton
    abstract fun provideAccountRepo(
        accountRepoImpl: AccountRepoImpl
    ) : AccountRepo

    @Binds
    @Singleton
    abstract fun provideAuthRepo(
       authRepoImpl: AuthRepoImpl
    ) : AuthRepo

    @Binds
    @Singleton
    abstract fun provideRoomRepo(
        roomRepoImpl: RoomRecipeRepoImpl
    ) : RoomRecipeRepository

    @Binds
    @Singleton
    abstract fun provideAiRepo(
        aiRepoImpl: AiRepoImpl
    ) : AIRepository

}