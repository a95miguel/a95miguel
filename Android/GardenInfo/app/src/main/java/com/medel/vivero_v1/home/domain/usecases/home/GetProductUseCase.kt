package com.medel.vivero_v1.home.domain.usecases.home

import com.medel.vivero_v1.home.domain.models.Product
import com.medel.vivero_v1.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetProductUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<List<Product>>{
        return repository.getAllProducts()
    }
}