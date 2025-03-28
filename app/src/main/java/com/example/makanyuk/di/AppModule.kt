package com.example.makanyuk.di

import com.example.makanyuk.data.network.firebase.auth.AccountRepoImpl
import com.example.makanyuk.data.network.firebase.auth.AuthRepoImpl
import com.example.makanyuk.data.network.retrofit.ApiService
import com.example.makanyuk.data.network.retrofit.RecipeRepoImpl
import com.example.makanyuk.model.auth.repo.AccountRepo
import com.example.makanyuk.model.auth.repo.AuthRepo
import com.example.makanyuk.model.recipe.repo.RecipeRepo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSpoonacularAPI() : ApiService{
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideAuthRepo(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ) : AuthRepo = AuthRepoImpl(
       firebaseAuth, firestore
    )

    @Provides
    @Singleton
    fun provideAccountRepo(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ) : AccountRepo = AccountRepoImpl(
        firebaseAuth,firestore
    )

    @Provides
    @Singleton
    fun provideRecipeRepo(
        apiService: ApiService
    ) : RecipeRepo = RecipeRepoImpl(apiService)

}