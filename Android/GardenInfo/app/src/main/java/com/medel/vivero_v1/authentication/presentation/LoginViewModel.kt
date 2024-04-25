package com.medel.vivero_v1.authentication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medel.vivero_v1.authentication.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Gestiona la logica de la pantalla de inicio de sesion
 *
 * @param loginUseCase Caso de uso para iniciar sesion
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val  loginUseCase: LoginUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher //Test
): ViewModel(){
    var state by mutableStateOf(LoginState())
        private set

    /**
     * Manejo de eventos
     */
    fun onEvent(event : LoginEvent){
        when(event){
            LoginEvent.Login -> {
                login()
            }
        }
    }

    /**
     * Inicia sesion y actualiza los estados
     */
    private fun login(){
        //Estado para mostrar CircularProgress
        state  = state.copy(
            isLoading = true
        )

        viewModelScope.launch(dispatcher) {
            loginUseCase.invoke().onSuccess {
                state = state.copy(
                    isLoggedIn = true
                )
            }
            state = state.copy(
                isLoading = false
            )
        }
    }

}
