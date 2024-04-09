package com.medel.vivero_v1.home.domain.repository

import com.medel.vivero_v1.home.domain.models.Product
import kotlinx.coroutines.flow.Flow

/**
 * Define los metodos para el repositorio de Home
 */
interface HomeRepository {
    fun getAllProducts (): Flow<List<Product>>
    suspend fun insertProduct(product: Product): Result<Unit>
    suspend fun getProductById(id : String) : Product
    suspend fun updateProducts(product : Product) : Result<Unit>
    suspend fun deleteProduct (id:String): Result<Unit>
}