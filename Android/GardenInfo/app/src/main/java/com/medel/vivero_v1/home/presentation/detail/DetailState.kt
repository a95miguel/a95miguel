package com.medel.vivero_v1.home.presentation.detail

import android.net.Uri
import com.medel.vivero_v1.home.domain.models.Product

data class DetailState(
    val id: String? = null,
    val name : String = "",
    val detail : String = "",
    val price : String = "",
    var imageUrl : Uri? = null,
    val nameError: String? = null,
    val detailError: String? = null,
    val priceError: String? = null,
    val imageUrlError: String? = null,
    val isSaved : Boolean = false,
    val isLoading : Boolean = false,
    val isLoadingUpdate : Boolean = false,
    val msgError : String? = null,
    val isCreated: Boolean = false,
    val isUpdate: Boolean = false,
    var products : List<Product> = emptyList()
)
