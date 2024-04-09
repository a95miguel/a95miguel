package com.medel.vivero_v1.authentication.presentation

data class LoginState(
    val isLoggedIn : Boolean = false,
    val isLoading : Boolean = false
)
