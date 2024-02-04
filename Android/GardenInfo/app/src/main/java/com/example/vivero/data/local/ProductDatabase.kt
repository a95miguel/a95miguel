package com.example.vivero.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vivero.domain.model.ProductLocal

/**
 * Configura la base de datos con Room
 *
 * @param dao  Instacia de [ProductDao] que proporciona metodos de acceso a los datos.
 */
@Database(entities = [ProductLocal::class], version = 1,exportSchema = false)
abstract class ProductDatabase : RoomDatabase(){
    abstract val dao : ProductDao
}