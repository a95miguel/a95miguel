package com.medel.vivero_v1.home.domain.usecases.detail

import com.medel.vivero_v1.home.domain.models.Product
import com.medel.vivero_v1.home.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProductByIdUseCase (
    private val repository: HomeRepository
){
    suspend operator fun invoke(id : String): Product{
        return withContext(Dispatchers.IO){
            repository.getProductById(id)
        }
    }
}