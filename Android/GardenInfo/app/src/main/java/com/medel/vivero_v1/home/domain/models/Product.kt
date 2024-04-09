package com.medel.vivero_v1.home.domain.models

/**
 * Representa los datos que seran utilizados en Firebase
 *
 * @param id Es el id unico del producto
 * @param name El nombre del producto
 * @param detail El detalle del producto
 * @param price El precio del producto
 * @param imageUrl La ruta de la imagen
 */
data class Product(
    var id : String ="",
    var name : String="",
    var detail : String="",
    var price : String="",
    var imageUrl : String=""
)
