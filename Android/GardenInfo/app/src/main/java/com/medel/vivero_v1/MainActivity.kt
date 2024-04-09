package com.medel.vivero_v1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.medel.vivero_v1.navigation.NavigationHost
import com.medel.vivero_v1.navigation.NavigationRoute
import com.medel.vivero_v1.ui.theme.Vivero_v1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Vivero_v1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(
                        navHostController = navController,
                        startDestination = getStartDestination(),
                        logout = {
                            viewModel.logout()
                        }
                    )
                }
            }
        }
    }

    private fun getStartDestination() : NavigationRoute{
        //Valida si el usuario inicio sesion.
        return if (viewModel.isLoggedIn){
            NavigationRoute.Home
        }else{
            NavigationRoute.LoginScreen
        }
    }

}

