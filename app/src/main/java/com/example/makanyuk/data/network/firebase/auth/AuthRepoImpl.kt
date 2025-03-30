package com.example.makanyuk.data.network.firebase.auth

import com.example.makanyuk.domain.auth.Account
import com.example.makanyuk.domain.auth.repo.AuthRepo
import com.example.makanyuk.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepo {
    override suspend fun loginEmail(email: String, password: String): Flow<Resource<AuthResult>> {
       return flow {
           emit(Resource.Loading())
           try {
               val data = auth.signInWithEmailAndPassword(email,password).await()
               emit(Resource.Success(data))
           } catch (e: Exception){
               emit(Resource.Error(e.localizedMessage))
           }
       }
    }

    override suspend fun registerEmail(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = auth.createUserWithEmailAndPassword(email, password).await()
                data.user?.let {
                    createAccount(
                        id = it.uid,
                        email = email,
                        name = name
                    )
                }
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }

    override suspend fun forgotPassword(email: String): Resource<Unit> {
        return try {

            auth.sendPasswordResetEmail(email).result
            Resource.Success(Unit)
        } catch (e: Exception){
            Resource.Error(e.localizedMessage)
        }
    }

    override suspend fun createAccount(id:String, name: String, email: String): Resource<Unit> {
        return try {
            firestore.collection("users").document(id).set(
                Account(
                    name = name,
                    id = id,
                    email = email
                )
            ).await()
            Resource.Success(Unit)
        } catch (e: Exception){
            Resource.Error(e.localizedMessage)
        }
    }

    override suspend fun getCurrentUser(): Flow<Resource<FirebaseUser>> {
      return flow {
          try {
              emit(Resource.Loading())
              val user = auth.currentUser
              if (user!=null){
                  emit(Resource.Success(user))
              }
          } catch (e: Exception){
              emit(Resource.Error(e.localizedMessage))
          }
      }
    }


}