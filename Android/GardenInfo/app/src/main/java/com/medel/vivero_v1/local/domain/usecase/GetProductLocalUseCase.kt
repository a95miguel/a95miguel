package com.medel.vivero_v1.local.domain.usecase

import com.medel.vivero_v1.home.data.local.entity.ProductEntity
import com.medel.vivero_v1.home.domain.repository.HomeRepository
import com.medel.vivero_v1.local.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetProductLocalUseCase(
    private val repository: LocalRepository
) {
    operator fun invoke() : Flow<List<ProductEntity>>{
        return repository.getAllProductsLocal()
    }
}