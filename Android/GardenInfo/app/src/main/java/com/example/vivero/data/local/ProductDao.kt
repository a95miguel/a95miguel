package com.example.vivero.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vivero.domain.model.ProductLocal
import kotlinx.coroutines.flow.Flow

/**
 * Define los metodos de acceso  a datos para la entidad  ProductoLocal en Room
 */
@Dao
interface ProductDao {
    /**
     * Inserta una lista de productos.
     *
     * @param product La lista  de [ProductLocal] que se va a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<ProductLocal>)

    /**
     * Obtiene la lista de productos  con Flow
     *
     * @param [Flow] que obtiene la lista de [ProductLocal].
     */
    @Query("SELECT * FROM ProductLocal")
    fun getProduct(): Flow<List<ProductLocal>>

    /**
     * Elimina un producto de la base de datos de ROOM.
     *
     * @param productId EL id que se va a eliminar
     */
    @Query("DELETE FROM ProductLocal WHERE id = :productId")
    suspend fun deleteProduct(productId : String)
}