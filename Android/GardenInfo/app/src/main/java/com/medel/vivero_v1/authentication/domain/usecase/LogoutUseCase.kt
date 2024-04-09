package com.medel.vivero_v1.authentication.domain.usecase

import com.medel.vivero_v1.authentication.domain.repository.AuthenticationRepository

/**
 * Caso de uso para cerrar sesion de la aplicaciòn actual
 *
 * @property repository Repositorio de autenticacion
 *
 */
class LogoutUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(){
        return repository.logout()
    }
}