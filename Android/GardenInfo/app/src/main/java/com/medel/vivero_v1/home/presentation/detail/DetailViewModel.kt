package com.medel.vivero_v1.home.presentation.detail.presentation

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medel.vivero_v1.home.domain.models.Product
import com.medel.vivero_v1.home.domain.usecases.detail.DetailUseCases
import com.medel.vivero_v1.home.domain.usecases.detail.ValidationResult
import com.medel.vivero_v1.home.presentation.detail.DetailEvent
import com.medel.vivero_v1.home.presentation.detail.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Maneja la logica de la vista de Detalles
 *
 * @param savedStateHandle Guarga el estado
 * @param detailUseCases Caso de usos relacionados con los detalles del producto
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailUseCases: DetailUseCases
) : ViewModel(){

    var state by mutableStateOf(DetailState())
        private set

    init {
        val id = savedStateHandle.get<String?>("productId")
        if(id != null){
            viewModelScope.launch {
                val product = detailUseCases.getProductByIdUseCase(id)
                state = state.copy(
                    id = product.id,
                    name = product.name,
                    detail = product.detail,
                    price = product.price,
                    imageUrl =  Uri.parse(product.imageUrl)
                )
            }
        }
    }

    fun onEvent(event : DetailEvent){
        when (event){
            is DetailEvent.DetailChange -> {
                state = state.copy(
                    detail = event.detail
                )
            }
            DetailEvent.DetailSave -> {
                createProduct()
            }
            is DetailEvent.NameChange -> {
                state = state.copy(
                    name = event.name
                )
            }
            is DetailEvent.PriceChange -> {
                state = state.copy(
                    price = event.price
                )
            }

            is DetailEvent.ImageUrlChange -> {
                state = state.copy(
                    imageUrl = event.imageUrl
                )
            }

            DetailEvent.DetailUpdate -> {
                updateProduct()
            }

            else -> {}
        }
    }

    /**
     * Crea un nuevo producto
     */
    private fun createProduct(){
        //Valida los campos del formulario
        valiteEditText()
        if(state.nameError == null && state.detailError == null && state.priceError ==null){
            viewModelScope.launch {
                state = state.copy(
                    isLoading = true
                )
                val product = Product(
                    id = "",
                    name = state.name,
                    detail = state.detail,
                    price = state.price,
                    imageUrl = state.imageUrl.toString()
                )
                //Ejecuta el caso de uso para insertar el produccto
                detailUseCases.createProductUseCase(product).onSuccess {
                    state = state.copy(
                        isLoading = false,
                        isCreated = true
                    )

                }.onFailure{
                    state = state.copy(
                        isLoading = false,
                        msgError = it.message
                    )
                }
            }
        }
    }

    /**
     * Actualiza un produto existente
     */
    private fun updateProduct(){
        valiteEditText()
        if(state.nameError == null && state.detailError == null && state.priceError ==null){
            state = state.copy(
                isLoadingUpdate = true
            )
            viewModelScope.launch {
                val product = Product(
                    id = state.id  ?: "",
                    name = state.name,
                    detail = state.detail,
                    price = state.price,
                    imageUrl = state.imageUrl.toString()
                )
                //Ejecuta el caso de uso para actualizar el produccto
                detailUseCases.updateProductUseCase(product).onSuccess {
                    state = state.copy(
                        isLoadingUpdate = false,
                        //isCreated = true
                    )
                    ///getProducts()
                }.onFailure{
                    state = state.copy(
                        isLoadingUpdate = false,
                        msgError = it.message
                    )
                }
            }
        }
    }

    /**
     * Valida los campos del formulario
     */
    private fun valiteEditText(){
        state = state.copy(
            nameError = null,
            detailError = null,
            priceError = null
        )

        //Se ejecuat el caso de uso con los estados
        val validationResult = detailUseCases.validateFormUseCase(state.name,state.detail,state.price)
        //Actualiza los estados en caso de un resultodo
        state = state.copy(
            nameError = getErrorMessage(validationResult.nameResult),
            detailError = getErrorMessage(validationResult.detailResult),
            priceError = getErrorMessage(validationResult.priceResult)
        )
    }

    /**
     * Obtine el mensaje de error de un resultado.
     */
    private fun getErrorMessage(result: ValidationResult): String? {
        return if (result is ValidationResult.Invalid) result.errorMessage else null
    }


}