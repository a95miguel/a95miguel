package com.medel.vivero_v1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medel.vivero_v1.authentication.domain.usecase.GetUserIdUseCase
import com.medel.vivero_v1.authentication.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Gestiona la sesion del usuario
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,//Obtiene el id
    private val logoutUseCase: LogoutUseCase//Cierra sesion
): ViewModel(){
    var isLoggedIn by mutableStateOf(getUserIdUseCase() != null)
    private set

    fun logout(){
        viewModelScope.launch {
            logoutUseCase()
        }
    }

}