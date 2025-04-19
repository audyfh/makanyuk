package com.example.makanyuk.di

import com.example.makanyuk.domain.auth.repo.AccountRepo
import com.example.makanyuk.domain.auth.repo.AuthRepo
import com.example.makanyuk.domain.auth.usecase.AuthUseCase
import com.example.makanyuk.domain.auth.usecase.ForgotPasswordUseCase
import com.example.makanyuk.domain.auth.usecase.GetCurrentUserUseCase
import com.example.makanyuk.domain.auth.usecase.GetUserProfileUseCase
import com.example.makanyuk.domain.auth.usecase.LoginUseCase
import com.example.makanyuk.domain.auth.usecase.LogoutUseCase
import com.example.makanyuk.domain.auth.usecase.RegisterUseCase
import com.example.makanyuk.domain.recipe.repo.RecipeRepo
import com.example.makanyuk.domain.recipe.repo.RoomRecipeRepository
import com.example.makanyuk.domain.recipe.usecase.local.DeleteLocalRecipe
import com.example.makanyuk.domain.recipe.usecase.local.GetLocalRecipeByID
import com.example.makanyuk.domain.recipe.usecase.local.GetLocalRecipes
import com.example.makanyuk.domain.recipe.usecase.local.InsertLocalRecipe
import com.example.makanyuk.domain.recipe.usecase.local.IsLocalRecipeSaved
import com.example.makanyuk.domain.recipe.usecase.local.LocalRecipeUseCases
import com.example.makanyuk.domain.recipe.usecase.network.GetMealPlanUseCase
import com.example.makanyuk.domain.recipe.usecase.network.GetRecipeByIdUseCase
import com.example.makanyuk.domain.recipe.usecase.network.GetRecipesUseCase
import com.example.makanyuk.domain.recipe.usecase.network.RecipeUseCases
import com.example.makanyuk.domain.recipe.usecase.network.SearchRecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(
        authRepo: AuthRepo,
        accountRepo: AccountRepo
    ) : AuthUseCase {
        return AuthUseCase(
            login = LoginUseCase(authRepo),
            register = RegisterUseCase(authRepo),
            logout = LogoutUseCase(authRepo),
            forgotPassword = ForgotPasswordUseCase(authRepo),
            getCurrentUser = GetCurrentUserUseCase(authRepo),
            getUserProfile = GetUserProfileUseCase(accountRepo)
        )
    }

    @Provides
    @Singleton
    fun provideRecipeUseCases(
        recipeRepo: RecipeRepo
    ) : RecipeUseCases {
        return RecipeUseCases(
            getRecipesUseCase = GetRecipesUseCase(recipeRepo),
            getRecipeByIdUseCase = GetRecipeByIdUseCase(recipeRepo),
            getMealPlanUseCase = GetMealPlanUseCase(recipeRepo),
            searchRecipeUseCase = SearchRecipeUseCase(recipeRepo)
        )
    }

    @Provides
    @Singleton
    fun provideLocalRecipeUseCases(
        roomRecipeRepository: RoomRecipeRepository
    ) : LocalRecipeUseCases {
        return LocalRecipeUseCases(
            getLocalRecipeByID = GetLocalRecipeByID(roomRecipeRepository),
            getLocalRecipes = GetLocalRecipes(roomRecipeRepository),
            insertLocalRecipe = InsertLocalRecipe(roomRecipeRepository),
            deleteLocalRecipe = DeleteLocalRecipe(roomRecipeRepository),
            isLocalRecipeSaved = IsLocalRecipeSaved(roomRecipeRepository)
        )
    }
}