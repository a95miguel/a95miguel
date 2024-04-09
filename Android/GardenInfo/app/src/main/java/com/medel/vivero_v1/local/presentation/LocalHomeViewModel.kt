package com.medel.vivero_v1.local.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medel.vivero_v1.local.domain.usecase.GetProductLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalHomeViewModel @Inject constructor (
    private val getProductLocalUseCase: GetProductLocalUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(LocalHomeState())
    val state : StateFlow<LocalHomeState> get() = _state

    init {
        getProduct()
    }

    fun onEvent (event : LocalEvent){
        when(event){
            is LocalEvent.SearchChange -> {
                _state.value= _state.value.copy(
                    textSearch = event.textSearch
                )
            }
        }
    }

    /**
     * Obtinen el listado de los datos almacenados en la ROOM
     */
    private fun getProduct(){
        viewModelScope.launch {
            getProductLocalUseCase.invoke().collectLatest {
                _state.value = _state.value.copy(
                    product = it
                )
            }
        }
    }

}