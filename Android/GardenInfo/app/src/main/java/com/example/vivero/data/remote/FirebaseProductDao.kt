package com.example.vivero.data.remote

import androidx.lifecycle.LiveData
import com.example.vivero.domain.model.Productos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Define los metodos para interactuar  con Firebase Realtime
 */
interface FirebaseProductDao {
    suspend fun signInAnonymously(): FirebaseUser?
    fun  auth(): FirebaseAuth?
    fun getProduct(): LiveData<List<Productos>>
    fun insertProduct(product: Productos, onComplete: (Boolean) -> Unit)
    fun updateImage(product: Productos, onComplete: (Boolean) -> Unit)
    fun updateProduct(product: Productos, onComplete: (Boolean) -> Unit)
    fun deleteProduct(id : String, onComplete: (Boolean) -> Unit)
    fun signOut()
}