package com.medel.vivero_v1.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.medel.vivero_v1.home.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

/**
 * Define los metodos de acceso a datos de la entidad ProductEntity en room
 */
@Dao
interface ProductDao {
    /**
     * Inserta y actualiza los datos  en la lista de ProductEntity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)

    /**
     * Obtiene el id en la lista de ProductEntity
     *
     * @param id EL id que se va a obtener
     */
    @Query("SELECT * FROM ProductEntity WHERE id = :id")
    suspend fun getProductById(id : String): ProductEntity

    /**
     * Obtiene los datos de ProductEntity con Flow
     *
     * @param [Flow] que obtiene la lista de [ProductEntity].
     */
    @Query("SELECT * FROM ProductEntity")
    fun getAllProducts() : Flow<List<ProductEntity>>

    /**
     * Elimina un dato en la lista de ProductEntity
     *
     * @param id EL id que se va a eliminar
     */
    @Query("DELETE FROM ProductEntity WHERE id = :id")
    suspend fun deleteProduct(id : String)

}