package com.medel.vivero_v1.authentication.domain.repository

/**
 * Define los metodos para el repositorio de authentication
 */
interface AuthenticationRepository {
    suspend fun login() : Result<Unit>
    fun getUserId() : String?
    suspend fun logout()
}