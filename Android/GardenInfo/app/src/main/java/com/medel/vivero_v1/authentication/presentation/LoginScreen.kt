package com.medel.vivero_v1.authentication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.vivero.network.ConnectivityObserver
import com.example.vivero.network.NetwokConnectivityObserver
import com.medel.vivero_v1.R

@Composable
fun LoginScreen(
    onLogin : ()-> Unit,
    viewModel : LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    val connectivityObserver = remember{ NetwokConnectivityObserver(context) }
    val status by connectivityObserver.observer().collectAsState(initial = ConnectivityObserver.Status.Unavailable)
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.planta))
    val progressLottie by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.progress))
    val notConnect = stringResource(R.string.not_conect_Internet)
    var enable = !(status == ConnectivityObserver.Status.Lost || status == ConnectivityObserver.Status.Limited || status == ConnectivityObserver.Status.Unavailable)

    //Efecto lanzado cuando el estado cambia
    LaunchedEffect(state.isLoggedIn) {
        //Si el usuario inicio sesion cambia de pantalla
        if (state.isLoggedIn) {
            onLogin()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            if (state.isLoading) {
                //Animacion lottie en progreso cuando inicia session
                LottieAnimation(
                    modifier = Modifier.size(250.dp),
                    composition = progressLottie,
                    iterations = LottieConstants.IterateForever // La animacion se repite
                )
            }else{
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(id = R.string.app_name),
                    fontSize = 30.sp,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.surfaceTint
                )

                LottieAnimation(
                    modifier = Modifier.size(200.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever // La animacion se repite
                )

                Button(
                    onClick = {
                        onLogin()
                        viewModel.onEvent(LoginEvent.Login)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.padding(10.dp),
                    enabled = enable
                ) {
                    Text(
                        text = stringResource(R.string.log_in),
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = Ellipsis
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.anonymous),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier.size(18.dp)
                    )
                }
                
                if(!enable){
                    Text(text = notConnect)
                }

            }
        }

    }
}
