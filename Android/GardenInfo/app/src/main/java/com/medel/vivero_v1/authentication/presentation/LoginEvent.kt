package com.medel.vivero_v1.authentication.presentation

sealed interface LoginEvent{
    object Login : LoginEvent
}