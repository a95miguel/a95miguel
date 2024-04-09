package com.medel.vivero_v1.home.domain.usecases.detail

import com.medel.vivero_v1.home.domain.models.Product
import com.medel.vivero_v1.home.domain.repository.HomeRepository

class CreateProductUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(product: Product) : Result<Unit>{
        return repository.insertProduct(product)
    }
}