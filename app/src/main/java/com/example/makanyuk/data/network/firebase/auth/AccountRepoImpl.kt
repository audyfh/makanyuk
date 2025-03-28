package com.example.makanyuk.data.network.firebase.auth

import com.example.makanyuk.model.auth.Account
import com.example.makanyuk.model.auth.repo.AccountRepo
import com.example.makanyuk.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountRepoImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AccountRepo {

    override suspend fun getUserProfile(): Resource<Account> {
       return try {
           val uid = auth.currentUser?.uid
          val data = uid?.let {
              firestore.collection("users").document(it).get().await()
          }
           val account = data?.toObject(Account::class.java)
           Resource.Success(account)
       } catch (e:Exception){
           Resource.Error(e.localizedMessage)
       }
    }
}