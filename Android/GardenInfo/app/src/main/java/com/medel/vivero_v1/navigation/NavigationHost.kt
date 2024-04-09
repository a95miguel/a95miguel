package com.medel.vivero_v1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.medel.vivero_v1.authentication.presentation.LoginScreen
import com.medel.vivero_v1.home.presentation.detail.DetailScreen
import com.medel.vivero_v1.home.presentation.home.HomeScreen
import com.medel.vivero_v1.home.presentation.home.components.FullScreenImage
import com.medel.vivero_v1.local.presentation.LocalScreen
import com.medel.vivero_v1.local.presentation.components.ErrorInternet

/**
 * Contenedor principal para la navegacion de pantallas.
 */
@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination : NavigationRoute,
    logout :()-> Unit
) {
    NavHost(navController = navHostController, startDestination = startDestination.route){
        composable(NavigationRoute.LoginScreen.route){
            LoginScreen(
                onLogin = {
                navHostController.navigate(NavigationRoute.Home.route)
                }
            )
        }

        composable(NavigationRoute.Home.route){
            HomeScreen(
                onLogout = {
                    logout()
                    navHostController.navigate(NavigationRoute.LoginScreen.route){
                        popUpTo(navHostController.graph.id){
                            inclusive = true
                        }
                    }
                },
               onFullImage = {imageUrl ->
                   navHostController.navigate("${NavigationRoute.FullScreenImage.route}?imageUrl=$imageUrl")
                },
                onCreateDetail = {
                    navHostController.navigate(NavigationRoute.DetailScreen.route)
                },
                onEditDetail = {
                    navHostController.navigate(NavigationRoute.DetailScreen.route + "?productId=$it")
                },
                onHomeLocal = {
                    navHostController.navigate(NavigationRoute.ErrorInternet.route){
                        popUpTo(navHostController.graph.id){
                            inclusive = true
                        }
                    }

                }
            )
        }

        composable(
            route = "${NavigationRoute.FullScreenImage.route}?imageUrl={imageUrl}",
            arguments = listOf(navArgument("imageUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            val imageUrl = backStackEntry.arguments?.getString("imageUrl").toString()
            FullScreenImage(
                onBack = { navHostController.popBackStack() },
                imageUrl = imageUrl
            )
        }

        composable(
            NavigationRoute.DetailScreen.route + "?productId={productId}",
            arguments = listOf(
                navArgument("productId"){
                    type= NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
            ){
            DetailScreen(
                onBack = {
                    navHostController.popBackStack()
                },
                onCreateBack = {
                    //navHostController.navigate(NavigationRoute.Home.route)
                    navHostController.popBackStack()
                }
            )
        }

        composable(
            NavigationRoute.ErrorInternet.route
        ){
            ErrorInternet(
                onHomeLocal = {
                    navHostController.navigate(NavigationRoute.LocalScreen.route){
                        popUpTo(navHostController.graph.id){
                            inclusive = true
                        }
                    }
                }
            )
        }


        composable(
            NavigationRoute.LocalScreen.route
        ){
            LocalScreen(
                onHomeScreen = {
                    navHostController.navigate(NavigationRoute.Home.route)
                }
            )
        }
    }
}