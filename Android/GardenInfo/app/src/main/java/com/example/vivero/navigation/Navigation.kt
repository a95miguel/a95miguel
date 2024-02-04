package com.example.vivero.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vivero.domain.model.Productos
import com.example.vivero.UI.view.AddProduct
import com.example.vivero.UI.view.ErrorInternet
import com.example.vivero.UI.view.FullScreenImage
import com.example.vivero.UI.view.Home
import com.example.vivero.UI.view.Sing
import com.example.vivero.UI.view.UseWithoutInternet
import com.example.vivero.UI.viewModel.ProductViewModel

/**
 * Define la navegacion entre activitys que van a interactuar.
 *
 * @param viewModel maneja los datos relacionados con los productos.
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(viewModel : ProductViewModel) {
    //Se crea rememberNavController() para navegar entre las pantallas
    val navController = rememberNavController()
    //se obtine el valor del viewModel, para que sea observado por el NavHost
    val result = viewModel.current.value
    //Validamos si el usuario a iniciado sesion, en caso de iniciar navega en la pantalla Home, en caso contratrio SignIn
    NavHost(navController = navController, startDestination = if(result!=null) Screen.Home.route else Screen.SignIn.route) {
        //Se define el composable de inicio
        composable(Screen.Home.route) {
            Home(navController,viewModel)
        }

        composable(Screen.SignIn.route){
            Sing(viewModel)
        }

        //Se define composable AddProduct con parametros
        composable(
            Screen.AddProduct.route+"/{id}/{name}/{detail}/{price}/{imageUrl}",
            arguments = listOf(
                navArgument("id"){type = NavType.StringType},
                navArgument("name") { type = NavType.StringType },
                navArgument("detail") { type = NavType.StringType; defaultValue = ""},
                navArgument("price"){ type = NavType.StringType},
                navArgument("imageUrl"){ type = NavType.StringType})
            ){entry->
            //Recupera los valores y los pasamos en el objecto
            val product = Productos(
                id = entry.arguments?.getString("id").toString(),
                name = entry.arguments?.getString("name").toString(),
                detail = entry.arguments?.getString("detail")?: "",
                price = entry.arguments?.getString("price").toString(),
                imageUrl =  entry.arguments?.getString("imageUrl").toString()
            )
            AddProduct(navController,viewModel,product)
        }

        composable(Screen.FullScreenImage.route+"/{imageUrl}"){entry->
            val imageUrl  = entry.arguments?.getString("imageUrl").toString()
            FullScreenImage(imageUrl = imageUrl,navController)
        }
        
        composable(Screen.ErrorInternet.route){
            ErrorInternet(navController)
        }

        composable(Screen.UseWithoutInternet.route){
            UseWithoutInternet(navController,viewModel)
        }
    }
}

/**
 * Representa las pantallas de la aplicacion.
 *
 * @param route Ruta asociada a la aplicaicon.
 */
sealed class Screen(val route : String){
    object Home : Screen("Home")
    object SignIn : Screen("SignIn")
    object AddProduct : Screen("Add")
    object ErrorInternet : Screen("ErrorInternet")
    object UseWithoutInternet : Screen("UseWithoutInternet")
    object FullScreenImage : Screen("FullScreenImage")
}