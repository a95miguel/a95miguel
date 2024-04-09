package com.medel.vivero_v1.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.medel.vivero_v1.home.data.local.entity.ProductEntity

/**
 * Configura la base de datos de Room
 *
 * @param dao  Instancia de [ProductDao] que proporciona metodos de acceso a los datos.
 */
@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase(){
    abstract val dao : ProductDao
}