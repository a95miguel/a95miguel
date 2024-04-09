package com.medel.vivero_v1.authentication.domain.usecase

import com.medel.vivero_v1.authentication.domain.repository.AuthenticationRepository

/**
 * Caso de uso para obtener el id del usuario
 *
 * @property repository Repositorio de autenticacion
 *
 * @return getUserId Identificador del usuario actual
 */
class GetUserIdUseCase(
    private val repository: AuthenticationRepository
) {
    operator fun invoke():String?{
        return repository.getUserId()
    }
}