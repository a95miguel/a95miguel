package com.medel.vivero_v1.local.domain.repository

import com.medel.vivero_v1.home.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getAllProductsLocal (): Flow<List<ProductEntity>>
}