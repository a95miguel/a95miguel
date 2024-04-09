package com.medel.vivero_v1.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa los datos que se van a almacenar en la base de datos de ROOM
 *
 *  @param id Es el id unico del producto
 *  @param name El nombre del producto
 *  @param detail El detalle del producto
 *  @param price El precio del producto
 *  @param imageUrl La ruta de la imagen
 */
@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val name : String,
    val detail : String,
    val price : String,
    val imageUrl : String
)
