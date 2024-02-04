package com.example.vivero.domain.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Como usa parcelize https://developer.android.com/kotlin/parcelize?hl=es-419
/**
 * Representa los datos que seran utilizados en Firebase
 *
 * @param id Es el id unico del producto
 * @param name El nombre del producto
 * @param detail El detalle del producto
 * @param price El precio del producto
 * @param imageUrl La url de la imagen del producto
 */
@Parcelize
data class Productos(
    var id : String ,
    var name : String ,
    var detail: String ,
    var price: String ,
    var imageUrl: String
) : Parcelable{
    //Constructor por defecto utilizado para la creacion de instancias.
    constructor():this("","","","","")
}