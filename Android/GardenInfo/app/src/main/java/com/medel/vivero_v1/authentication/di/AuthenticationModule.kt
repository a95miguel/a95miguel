package com.medel.vivero_v1.authentication.di

import com.medel.vivero_v1.authentication.data.repository.AuthenticationRepositoryImpl
import com.medel.vivero_v1.authentication.domain.repository.AuthenticationRepository
import com.medel.vivero_v1.authentication.domain.usecase.GetUserIdUseCase
import com.medel.vivero_v1.authentication.domain.usecase.LoginUseCase
import com.medel.vivero_v1.authentication.domain.usecase.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Instancias de depedencias para la aplicaciòn
 */
@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {
    /**
     * Instacia de [AuthenticationRepository] que implementa la logica de Authenticacion
     */
    @Provides
    @Singleton
    fun provideAuthenticationRepository () : AuthenticationRepository{
        return AuthenticationRepositoryImpl()
    }

    /**
     * Intancia de [LoginUseCase] para el repositorio de autenticacion
     */
    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthenticationRepository) : LoginUseCase{
        return LoginUseCase(repository)
    }

    /**
     * Intancia de [GetUserIdUseCase] para el repositorio de autenticacion
     */
    @Provides
    @Singleton
    fun provideGetUserIdUseCase(repository: AuthenticationRepository): GetUserIdUseCase {
        return GetUserIdUseCase(repository)
    }

    /**
     * Intancia de [LogoutUseCase] para el repositorio de autenticacion
     */
    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthenticationRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }
}