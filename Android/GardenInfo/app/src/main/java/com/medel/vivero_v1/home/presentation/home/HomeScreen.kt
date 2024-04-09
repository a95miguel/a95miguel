package com.medel.vivero_v1.home.presentation.home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.vivero.network.ConnectivityObserver
import com.example.vivero.network.NetwokConnectivityObserver
import com.medel.vivero_v1.home.presentation.home.components.Search
import com.medel.vivero_v1.R
import com.medel.vivero_v1.home.presentation.home.components.HomeProduct
import com.medel.vivero_v1.home.presentation.home.components.LogoutDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Pantalla principal de la aplicacion, donde se muetran el listados de los productos.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onFullImage : (String)->Unit,
    viewModel : HomeViewModel = hiltViewModel(),
    onCreateDetail : () -> Unit,
    onEditDetail : (String)->Unit,
    onHomeLocal : ()->Unit
) {
    //Obtiene el estado del ViewModel
    val state = viewModel.state.collectAsState().value
    val progressLottie by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.progress))
    //Estado para la posicion de desplazamiento de la lista
    val listState = rememberLazyListState()
    //Estado para mostar el boton flotante
    val showFab  by remember{ derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    val coroutineScope = rememberCoroutineScope()
    //Configura el composrtamiento de desplazamiento para la barra superior
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()//Para ocultar topBar
    val context = LocalContext.current
    //Observador de la conexion de red
    val connectivityObserver = remember{ NetwokConnectivityObserver(context)}
    val status by connectivityObserver.observer().collectAsState(initial = ConnectivityObserver.Status.Unavailable)

    //Cambia de pantalla en caso de perdida de internet
    if(status == ConnectivityObserver.Status.Lost || status == ConnectivityObserver.Status.Unavailable || status == ConnectivityObserver.Status.Limited){
        LaunchedEffect(Unit){
            delay(1000)
            onHomeLocal()
        }
    }

    if(state.isLoading){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary),
            contentAlignment = Alignment.Center){
            LottieAnimation(
                modifier = Modifier.size(250.dp),
                composition = progressLottie,
                iterations = LottieConstants.IterateForever // La animacion se repite
            )
        }
    } else{
        Scaffold (
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                            Search(value = state.textSearch, onValueChange = { viewModel.onEvent(HomeEvent.SearchChange(it))})
                    },
                    actions = {
                        IconButton(onClick = { viewModel.onEvent(HomeEvent.ShowDialog(true))}) {
                            Image(
                                painter = painterResource(id = R.drawable.exit),
                                contentDescription = "Exit App",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            floatingActionButton = {
                Column (
                    horizontalAlignment = Alignment.End
                ){
                    if(showFab){
                        FloatingActionButton(
                            modifier = Modifier.padding(bottom = 10.dp),
                            containerColor = Color.White,
                            shape = CircleShape,
                            onClick = {
                                coroutineScope.launch {
                                    listState.scrollToItem(0)
                                }
                            }) {
                            Image(
                                painter = painterResource(id = R.drawable.arrowup),
                                contentDescription = "Arrow up",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                    FloatingActionButton(
                        onClick = onCreateDetail,
                        containerColor = Color.White
                    ) {
                        Image(painter = painterResource(id = R.drawable.addproduct), contentDescription = "Add product", modifier = Modifier.size(50.dp))
                    }
                }
            }
        ){
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(bottom = 40.dp, top = 10.dp),
                state = listState
            ){
                items(state.products){
                    val encodedUrl = Uri.encode(it.imageUrl)
                    HomeProduct(
                        viewModel :: onEvent,
                        state,
                        product = it,
                        textSearch = state.textSearch,
                        expanded = state.expanded,
                        onFullImage = { onFullImage(encodedUrl)},
                        onClickUpdate = {
                            onEditDetail(it.id)
                        }
                    )
                }

            }

            if (state.showDialogExit){
                LogoutDialog(
                    title = stringResource(R.string.close_app),
                    text = stringResource(R.string.confirm_close_app),
                    onClick = {
                        viewModel.onEvent(HomeEvent.DismissDialog(false))
                        onLogout()
                    }
                ) {
                    viewModel.onEvent(HomeEvent.DismissDialog(false))
                }
            }

        }
    }
}
