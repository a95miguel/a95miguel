package com.medel.vivero_v1.navigation

sealed class NavigationRoute (val route : String){
    object LoginScreen : NavigationRoute("login")
    object Home : NavigationRoute("home")
    object FullScreenImage : NavigationRoute("fullImage")
    object DetailScreen : NavigationRoute("detail")
    object ErrorInternet : NavigationRoute("errorInternet")
    object LocalScreen : NavigationRoute("local")
}
