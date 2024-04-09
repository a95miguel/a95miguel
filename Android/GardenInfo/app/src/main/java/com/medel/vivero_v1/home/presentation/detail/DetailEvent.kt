package com.medel.vivero_v1.home.presentation.detail

import android.net.Uri

/**
 * Define los eventos que se va a utlizar en detalles
 */
sealed interface DetailEvent{
    data class NameChange(val name : String) : DetailEvent
    data class DetailChange(val detail : String) : DetailEvent
    data class PriceChange(val price : String) : DetailEvent
    data class ImageUrlChange(val imageUrl : Uri?) : DetailEvent
    object DetailSave : DetailEvent
    object DetailUpdate : DetailEvent
}