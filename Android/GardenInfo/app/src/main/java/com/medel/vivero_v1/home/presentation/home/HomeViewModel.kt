package com.medel.vivero_v1.home.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medel.vivero_v1.authentication.core.di.IoDispatcher
import com.medel.vivero_v1.home.domain.usecases.home.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
    @IoDispatcher private val dispatcher : CoroutineDispatcher//test
) : ViewModel(){

    private val _state = MutableStateFlow(HomeState())
    val state : StateFlow<HomeState> get() = _state


    init {
        _state.value = _state.value.copy(
            isLoading = true
        )
        getProducts()
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.SearchChange -> {
                _state.value = _state.value.copy(
                    textSearch = event.textSearch
                )
            }

            is HomeEvent.ShowDialog -> {
                _state.value = _state.value.copy(
                    showDialogExit = true
                )
            }

            is HomeEvent.DismissDialog -> {
                _state.value = _state.value.copy(
                    showDialogExit = false,
                    showDialogDelete = false
                )
            }

            is HomeEvent.ShowDialogDelete -> {
                _state.value = _state.value.copy(
                    showDialogDelete = true
                )
            }

            is HomeEvent.DeleteChange -> {
                _state.value = _state.value.copy(
                    delete = event.delete
                )
                Log.d("DeleteProduct",_state.value.delete)
                deleteProduct()
            }
        }
    }

    private fun getProducts(){
        viewModelScope.launch {
            homeUseCases.getProductUseCase().collectLatest{
                Log.d("Lista Product",_state.value.isChangeData.toString())
                _state.value = _state.value.copy(
                    isLoading = false,
                    products = it
                )
            }
            _state.value = _state.value.copy(
                isLoading = false
            )
        }
    }

    private fun  deleteProduct(){
        _state.value = _state.value.copy(
            isLoadingDelete = true
        )
        viewModelScope.launch (dispatcher){
            //Llama el caso de uso  para eliminar y maneja un resultado
            homeUseCases.deleteProductUseCase(_state.value.delete).onSuccess {
                _state.value = _state.value.copy(
                    isLoadingDelete = false,
                    showDialogDelete = false
                )
                getProducts()
            }.onFailure {
                _state.value = _state.value.copy(
                    isLoadingDelete = false,
                    showDialogDelete = false,
                    msgError = it.message
                )
            }
        }
    }

}
