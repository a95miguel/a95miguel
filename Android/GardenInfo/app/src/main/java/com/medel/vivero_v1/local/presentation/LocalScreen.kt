package com.medel.vivero_v1.local.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import com.medel.vivero_v1.R
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vivero.network.ConnectivityObserver
import com.example.vivero.network.NetwokConnectivityObserver
import com.medel.vivero_v1.home.presentation.home.components.Search
import com.medel.vivero_v1.local.presentation.components.LocalHomeProduct
import com.medel.vivero_v1.local.presentation.components.NotAccessMsg
import kotlinx.coroutines.launch

/***
 * Se muestra la pantalla cuando no hay conexion a internet
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalScreen(
    onHomeScreen : ()->Unit,
    viewModel: LocalHomeViewModel = hiltViewModel()
) {
    //Estado para la posicion de desplazamiento de la lista
    val listState = rememberLazyListState()
    //Estado para mostar el boton flotante
    val showFab  by remember{ derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    val coroutineScope = rememberCoroutineScope()
    //Configura el composrtamiento de desplazamiento para la barra superior
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()//Para ocultar topBar
    val context = LocalContext.current
    //Observa la conexion de red
    val connectivityObserver = remember{ NetwokConnectivityObserver(context) }
    val status by connectivityObserver.observer().collectAsState(initial = ConnectivityObserver.Status.Unavailable)

    //En caso de conexion a la red cambia de pantalla
    if(status == ConnectivityObserver.Status.Available){
        onHomeScreen()
    }
    val state = viewModel.state.collectAsState().value
    
    if(state.product.isEmpty()){
        NotAccessMsg(text = stringResource(R.string.null_data_room), imagePainter = painterResource(
            id = R.drawable.denegated
        ))
    }else{
        Scaffold (
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor =MaterialTheme.colorScheme.surfaceVariant
                    ),
                    title = {
                        Search(value = state.textSearch, onValueChange = {viewModel.onEvent(
                            LocalEvent.SearchChange(it)) })
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            floatingActionButton = {
                if(showFab){
                    FloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.scrollToItem(0)
                            }
                        },
                        containerColor = Color.White,
                        shape = CircleShape
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.arrowup),
                            contentDescription = "Arrow up",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }

            }
        ){
            /*val productList = List(20) { index ->
                ProductEntity(
                    id = (index + 1).toString(),
                    name = "Product ${index + 1}",
                    detail = "Detail for product ${index + 1}",
                    price = "${(10.0 + index) * 2}",
                    imageUrl = "https://example.com/image_$index.png"
                )
            }*/
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ){
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 15.dp),
                    state = listState
                ){
                    items(state.product){productList->
                        LocalHomeProduct(
                            product = productList,
                            textSearch = state.textSearch
                        )
                    }
                }
            }
        }
    }
    
}

/*
@PreviewFontScale
@Composable
fun LocalPreview() {
    LocalScreen {

    }
}*/