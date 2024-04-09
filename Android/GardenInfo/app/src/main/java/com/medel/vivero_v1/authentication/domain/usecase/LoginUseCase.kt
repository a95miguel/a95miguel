package com.medel.vivero_v1.authentication.domain.usecase

import com.medel.vivero_v1.authentication.domain.repository.AuthenticationRepository

/**
 * Caso de uso para inicar sesion de la aplicaciòn.
 *
 * @property repository Repositorio de autenticacion
 *
 */

class LoginUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke() : Result<Unit>{
        return repository.login()
    }
}