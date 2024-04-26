package com.medel.vivero_v1.home.data.repository

import com.medel.vivero_v1.home.domain.models.Product
import com.medel.vivero_v1.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeHomeRepository : HomeRepository{
    var fakeError = false
    val fakeErrorMessagge = "Sever Error!!"

    // Simulación de una lista de productos
    private val productList = mutableListOf<Product>()
    private val productsFlow = MutableStateFlow<List<Product>>(emptyList())

    override fun getAllProducts(): Flow<List<Product>> {
        return productsFlow.asStateFlow()
    }

    override suspend fun insertProduct(product: Product): Result<Unit> {
        return if(fakeError){
            Result.failure(Exception(fakeErrorMessagge))
        }else{
            productList.add(product)
            productsFlow.value = productList.toList()
            Result.success(Unit)
        }
    }

    override suspend fun getProductById(id: String): Product {
        return  productList.find { it.id == id } ?: throw NoSuchElementException("$id not found")
    }

    override suspend fun updateProducts(product: Product): Result<Unit> {
        return if(fakeError){
            Result.failure(Exception(fakeErrorMessagge))
        }else{
            Result.success(Unit)
        }
    }

    override suspend fun deleteProduct(id: String): Result<Unit> {
        return if(fakeError){
            Result.failure(Exception(fakeErrorMessagge))
        }else{
            productList.removeAll{it.id == id}
            productsFlow.value = productList.toList()
            Result.success(Unit)
        }
    }

}