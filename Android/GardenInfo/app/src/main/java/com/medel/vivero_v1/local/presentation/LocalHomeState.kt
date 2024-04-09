package com.medel.vivero_v1.local.presentation

import com.medel.vivero_v1.home.data.local.entity.ProductEntity

data class LocalHomeState (
    val  product: List<ProductEntity> = emptyList(),
    var textSearch : String = ""
)