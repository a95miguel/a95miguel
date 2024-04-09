package com.medel.vivero_v1.home.domain.usecases.home

import com.medel.vivero_v1.home.domain.repository.HomeRepository

class DeleteProductUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(id : String) : Result<Unit>{
        return repository.deleteProduct(id)
    }
}