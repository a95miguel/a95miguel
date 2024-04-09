package com.medel.vivero_v1.local.data

import com.medel.vivero_v1.home.data.local.ProductDao
import com.medel.vivero_v1.home.data.local.entity.ProductEntity
import com.medel.vivero_v1.local.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementacion de [LocalRepository] que utiliza Room para el almacenamiento local.
 *
 * @property dao Dao para acceder a la base de datos local
 */
class LocalRepositoryImpl(
    private val dao : ProductDao
) : LocalRepository{
    /**
     * Obtinen los datos de productos en ROOM
     */
    override fun getAllProductsLocal(): Flow<List<ProductEntity>> {
        return dao.getAllProducts()
    }

}