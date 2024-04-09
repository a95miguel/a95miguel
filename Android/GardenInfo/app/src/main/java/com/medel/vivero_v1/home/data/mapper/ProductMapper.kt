package com.medel.vivero_v1.home.data.mapper

import com.medel.vivero_v1.home.data.local.entity.ProductEntity
import com.medel.vivero_v1.home.domain.models.Product

/**
 * Convierte un objeto [ProductEntity] a un objeto [Product].
 */
fun ProductEntity.toDomain(): Product{
    return Product(
        id = this.id,
        name = this.name,
        detail = this.detail,
        price = this.price,
        imageUrl = this.imageUrl
    )
}

/**
 * Convierte un objeto [Product] a un objeto [ProductEntity].
 */
fun Product.toEntity():ProductEntity{
    return ProductEntity(
        id = this.id,
        name = this.name,
        detail = this.detail,
        price = this.price,
        imageUrl = this.imageUrl.toString()
    )

}