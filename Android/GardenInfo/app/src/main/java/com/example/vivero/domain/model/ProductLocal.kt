package com.example.vivero.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa los datos que se van a almacenar en la base de datos de ROOM
 *
 * @param id Es el id unico del producto
 * @param name El nombre del producto
 * @param detail El detalle del producto
 * @param price El precio del producto
 */
@Entity
data class ProductLocal(
    @PrimaryKey
    var id : String,
    var name:String,
    var detail:String,
    var price:String
)

/**
 * Estado de la lista de productos de la aplicacion.
 *
 * @param listPlantas La lista de [ProductLocal] que representa el estado actual.
 */
data class PlantasState(
    val listPlantas : List<ProductLocal> = emptyList()
)