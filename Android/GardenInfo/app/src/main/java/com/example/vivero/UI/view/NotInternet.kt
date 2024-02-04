package com.example.vivero.UI.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vivero.navigation.Screen
import com.example.vivero.network.ConnectivityObserver
import com.example.vivero.network.NetwokConnectivityObserver
import kotlinx.coroutines.delay
import com.example.vivero.R
import com.example.vivero.UI.viewModel.ProductViewModel
import kotlinx.coroutines.launch

/**
 * Composable para mostrar una pantalla de error de conexion de internet.
 * Se utliza LaunchedEffect para navegar a otra pantalla despues de 2500 milisegundos.
 *
 * @param navigation Controlador de navegacion para cambiar a otra pantalla
 */
@Composable
fun ErrorInternet(
    navigation : NavController
) {
    //unDraw pagina donde se desgargan las ilustraciones para SVG
    NotAccesMsg(
        text = stringResource(R.string.not_conect_Internet),
        imagePainter = painterResource(id = R.drawable.notinternet)
    )

    LaunchedEffect(Unit){
        delay(2500)
        navigation.navigate(Screen.UseWithoutInternet.route)
    }
}

/**
 * Composable que muestra  un listado de datos almacenados localmente (ROOM) y proporciona una
 * opcion de busqueda , observa la conexion de red
 *
 * @param navigation Controlador de navegacion para cambiar a otra pantalla
 *
 * @param viewModel proporciona los datos y la logica de la aplicaion
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UseWithoutInternet(
    navigation : NavController,
    viewModel : ProductViewModel
) {
    //Obtine la lista de productos del viewModel
    val products = viewModel.state.listPlantas
    //Obtine el estado de la red
    val context = LocalContext.current
    val connectivityObserver = remember{ NetwokConnectivityObserver(context) }
    val status by connectivityObserver.observer().collectAsState(initial = ConnectivityObserver.Status.Unavailable)
    //Rastrea si ya se ha navegado en la pantalla
    var hasNavigated by remember { mutableStateOf(false) }
    val textSearch = remember { mutableStateOf(TextFieldValue("")) }
    //Estado para la posicion de desplazamiento de la lista
    val listState = rememberLazyListState()
    //Estado para mostar el boton flotante
    val showFab  by remember{ derivedStateOf { listState.firstVisibleItemIndex > 0 }}
    //Configura el composrtamiento de desplazamiento para la barra superior
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()//Para ocultar topBar

    //Verefica si hay conexion a interner, para cambiar de pantalla
    if(status == ConnectivityObserver.Status.Available && !hasNavigated){
        hasNavigated = true
        navigation.navigate(Screen.Home.route)
    }

    if(products.isEmpty()){
        NotAccesMsg(text = stringResource(R.string.null_data_room), imagePainter = painterResource(id = R.drawable.denegated))
    }else{
        Scaffold (
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    title = {
                    SearchView(state = textSearch)
                },
                    scrollBehavior = scrollBehavior
                )
            },
            floatingActionButton = {
                FloatingActionView(
                    showFab = showFab,
                    listState = listState,
                    showFloatindAdd = false,
                    navigation = navigation
                )
            }
        ){contentPadding ->
            Box (
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ){
                LazyColumn(
                    state = listState
                ){
                    items(products){listProduct->
                        if(listProduct.name.contains(textSearch.value.text,ignoreCase = true)){
                            OutlinedCard(
                                modifier = Modifier.padding(13.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                                ),
                                border = BorderStroke(1.dp,MaterialTheme.colorScheme.onBackground),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Column (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Text(
                                        text = listProduct.name,
                                        maxLines = 3,
                                        fontSize = 20.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                    if(listProduct.detail.isNotEmpty()){
                                        Text(
                                            text = listProduct.detail,
                                            maxLines = 3,
                                            fontSize = 20.sp,
                                            overflow = TextOverflow.Ellipsis,
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    }

                                    Text(
                                        text = "$"+listProduct.price,
                                        maxLines = 1,
                                        fontSize = 20.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )

                                }

                            }
                        }
                    }
                }
            }

        }
    }
    //En caso de retornar se sale de la aplicacion
    BackHandler()
}


/**
 * Composable maneja el evento de retroceso del sistema operativo. Cuando se
 * presiona el boton de retroceso se cerrara de la actividad actual.
 */
@Composable
fun BackHandler() {
    val backDispatcher =( LocalOnBackPressedDispatcherOwner.current as OnBackPressedDispatcherOwner).onBackPressedDispatcher
    val context = LocalView.current.context
    val backCallback = rememberUpdatedState{
        if(context is Activity){
            //Cierra la actividad actual
            context.finishAffinity()
        }
    }

    DisposableEffect(key1 = backCallback){
        //Agrega al dispacher el retroceso
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                backCallback.value()
            }
        }
        backDispatcher.addCallback(callback)

        //Elimina el callback al finalizar el efecto
        onDispose {
            callback.remove()
        }
    }
}

/**
 * Composable que muestra un mensaje de error con una imagen.
 *
 * @param text EL messaje de error.
 *
 * @param imagePainter Imagen que se mostrara.
 *
 */
@Composable
fun NotAccesMsg(
    text : String,
    imagePainter : Painter
) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                modifier = Modifier.padding(5.dp),
                text = text,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Image(
                modifier = Modifier.size(200.dp),
                painter = imagePainter,
                contentDescription = "Error menssage"
            )
        }
}

/**
 * Composable que muestra dos botones flotamtes, uno que se utliza para volver arriba de la lista de productos
 * y el otro para agregar nuevos elementos de los productos.
 *
 * @param showFab Indica si se debe de mostrar el boton flotante de volver arriba
 * @param listState El estado de la lista para controlar la posicion de desplazamiento
 * @param showFloatindAdd Indica si se debe de mostrar el boton flotante de agregar
 * @param navigation Controlador de navegacion para cambiar a otra pantalla
 */
@Composable
fun FloatingActionView(
    showFab : Boolean,
    listState : LazyListState,
    showFloatindAdd : Boolean,
    navigation: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    Column (
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxWidth()
    ){
        //Muestra el boton de volver arriba
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
        //Muestra el boton de agregar elementos
        if(showFloatindAdd){
            FloatingActionButton(
                containerColor = Color.White,
                onClick = {
                val res=null
                navigation.navigate("${Screen.AddProduct.route}/$res/$res/$res/$res/$res")
            }) {
                Image(
                    painter = painterResource(id = R.drawable.addproduct),
                    contentDescription = "add",
                    modifier =  Modifier.size(50.dp)
                )
            }
        }
    }
}
