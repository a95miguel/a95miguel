package com.medel.vivero_v1.local.presentation

sealed interface LocalEvent {
    data class SearchChange(val textSearch : String) : LocalEvent
}