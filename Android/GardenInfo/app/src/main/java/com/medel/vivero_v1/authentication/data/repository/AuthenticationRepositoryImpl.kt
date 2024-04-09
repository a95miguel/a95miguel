package com.medel.vivero_v1.authentication.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.medel.vivero_v1.authentication.domain.repository.AuthenticationRepository
import kotlinx.coroutines.tasks.await

/**
 * Implemenataciòn de [AuthenticationRepository] que interactua con Firebase Authentication
 */
class AuthenticationRepositoryImpl : AuthenticationRepository {
    /**
     * Inicia sesiòn de forma anonima utilizando Firebase Authentication.
     * @return Resultado de la operación. [Result.success] si la autenticación es exitosa, [Result.failure] si hay algún error.
     */
    override suspend fun login(): Result<Unit> {
        return try{
            //Intente iniciar sesiòn anonima y espera
            val authResult = Firebase.auth.signInAnonymously().await()
            Log.d("Authentication", "Usuario autenticado exitosamente: ${authResult.user?.uid}")
            //Devuelve resultado exitoso
            Result.success(Unit)
        }catch (e : FirebaseAuthException){
            Log.e("Login", "Error de autenticación: ${e.errorCode}")
            Result.failure(e)
        } catch (e: Exception) {
            Log.e("Login", "Error desconocido: ${e.message}")
            //Devuelve resultado fallido con la exception
            Result.failure(e)
        }
    }

    /**
     * Obtine el id del usuario
     */
    override fun getUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    /**
     * Cierra la sesion actual del usuario utilizado.
     */
    override suspend fun logout() {
        Firebase.auth.signOut()
    }
}