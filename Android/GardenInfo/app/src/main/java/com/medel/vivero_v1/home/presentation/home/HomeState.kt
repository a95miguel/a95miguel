package com.medel.vivero_v1.home.presentation.home

import com.medel.vivero_v1.home.domain.models.Product

data class HomeState(
    var products : List<Product> = emptyList(),
    var expanded : Boolean = false,
    var fullImage : Boolean = false,
    var textSearch : String = "",
    var showDialogExit : Boolean = false,
    var showDialogDelete : Boolean = false,
    var isLoading : Boolean = true,
    val isLoadingDelete : Boolean = false,
    val msgError : String? = null,
    var hasNavigated : Boolean = false,
    var delete : String = "",
    var isChangeData : Boolean = false
)
