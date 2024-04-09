package com.medel.vivero_v1.home.presentation.home

sealed interface HomeEvent{
    data class SearchChange(val textSearch : String) : HomeEvent
    data class ShowDialog(val showDialog : Boolean) : HomeEvent
    data class ShowDialogDelete(val showDialog : Boolean) : HomeEvent
    data class DismissDialog(val showDialog : Boolean) : HomeEvent
    data class DeleteChange(val delete : String) : HomeEvent
    //object DeleteProduct : HomeEvent
}