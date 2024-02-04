package com.example.vivero.UI.view

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.vivero.navigation.Screen
import com.example.vivero.UI.viewModel.ProductViewModel
import kotlinx.coroutines.launch
import com.example.vivero.R
import com.example.vivero.domain.model.Productos
import com.example.vivero.network.ConnectivityObserver
import com.example.vivero.network.NetwokConnectivityObserver
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.delay

/**
 * Muestra el listado de datos almacenados en Firebase Realtime, con opcion de busqueda, agregar, actualizar y eliminar elementos
 * de productos y observa la conexion de red
 *
 * @param navigation Controlador de navegacion para cambiar a otra pantalla
 *
 * @param viewModel proporciona los datos y la logica de la aplicaion
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    navigation : NavController,
    viewModel : ProductViewModel
) {
    //Obtine la lista de productos del viewModel
    val products: List<Productos> = viewModel.products.value
    val textSearch = remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialogExit by remember { mutableStateOf(false) }
    var showScaffold by remember { mutableStateOf(false) }
    val progressVisibleState = remember { mutableStateOf(false) }
    //Obtine el estado de la red
    val context = LocalContext.current
    val connectivityObserver = remember{ NetwokConnectivityObserver(context)}
    val status by connectivityObserver.observer().collectAsState(initial = ConnectivityObserver.Status.Unavailable)
    //Rastrea si ya se ha navegado en la pantalla
    var hasNavigated by remember { mutableStateOf(false) }
    val progressLottie by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.progress))
    //Estado para la posicion de desplazamiento de la lista
    val listState = rememberLazyListState()
    //Estado para mostar el boton flotante
    val showFab  by remember{ derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    //Configura el composrtamiento de desplazamiento para la barra superior
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()//Para ocultar topBar
    //val result = viewModel.current.value


    //Verefica si hay conexion a interner, para cambiar de pantalla
    if(status == ConnectivityObserver.Status.Lost && !hasNavigated || status == ConnectivityObserver.Status.Unavailable && !hasNavigated){
        LaunchedEffect(Unit){
            delay(2000)
            hasNavigated = true
            navigation.navigate(Screen.ErrorInternet.route)
        }

    }

    //Comprueba si el usuario esta autenticado
  //  if(result !=null){

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary//Principal
                ),
                title = {
                    if (showScaffold) {
                        SearchView(state = textSearch)
                    }
                },
                actions = {
                    if (showScaffold) {
                        val colorImage = MaterialTheme.colorScheme.onPrimaryContainer
                        IconButton(onClick = { showDialog = true }) {
                            Image(
                                painter = painterResource(id = R.drawable.exit),
                                contentDescription = "Exit to app",
                                colorFilter = ColorFilter.tint(colorImage)
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            if (showScaffold) {
                FloatingActionView(
                    showFab = showFab,
                    listState = listState,
                    showFloatindAdd = true,
                    navigation = navigation,
                )
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary),//Principal),
            contentAlignment = Alignment.Center) {
            if(products.any{it.id=="Loading"}){
                LottieAnimation(
                    modifier = Modifier.size(250.dp),
                    composition = progressLottie,
                    iterations = LottieConstants.IterateForever // La animacion se repite
                )
            }else{
                if(products.isNotEmpty()){
                    showScaffold = true
                    LazyColumn(
                        contentPadding = PaddingValues(10.dp),//horizontal = 8.dp, vertical = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(25.dp),
                        state = listState
                    ) {
                        items(products) { listProduct ->
                            if (listProduct.name.contains(textSearch.value.text, ignoreCase = true)) {
                                var expanded by remember{ mutableStateOf(false) }
                                val id = listProduct.id
                                val name = listProduct.name
                                var detail = listProduct.detail
                                val price = listProduct.price
                                val image = listProduct.imageUrl
                                val painter = rememberAsyncImagePainter(model = image)
                                val state = painter.state

                                Card(
                                    shape = RoundedCornerShape(15.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                                ){
                                    Column{
                                        Box (modifier = Modifier
                                            .height(250.dp)
                                            .fillMaxWidth()){
                                            if(state is AsyncImagePainter.State.Loading){
                                                CircularProgressIndicator(
                                                    strokeWidth = 3.dp,
                                                    modifier = Modifier
                                                        .align(Alignment.Center)
                                                        .size(30.dp),
                                                    color = MaterialTheme.colorScheme.inversePrimary//Color.Green
                                                )
                                            }
                                            Image(
                                                painter = painter,
                                                contentDescription = "Image url",
                                                modifier = Modifier.fillMaxSize(),
                                                contentScale = ContentScale.Crop
                                            )
                                            IconButton(
                                                onClick = { expanded = !expanded },
                                                modifier = Modifier.align(Alignment.TopEnd)
                                            ) {
                                                Icon(
                                                    modifier = Modifier.size(40.dp),
                                                    painter = if (expanded) painterResource(id = R.drawable.expand_less) else painterResource(
                                                        id = R.drawable.expand_more,
                                                    ),
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onPrimaryContainer//ButtonCotainer
                                                )
                                            }
                                            
                                            IconButton(
                                                onClick = { 
                                                val encodedUrl = Uri.encode(image)
                                                navigation.navigate("${Screen.FullScreenImage.route}/$encodedUrl")
                                                },
                                                modifier = Modifier.align(Alignment.TopStart)
                                                ) {
                                                Icon(
                                                    modifier = Modifier.size(40.dp),
                                                    painter = painterResource(id = R.drawable.fullscreen),
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                                )                                                
                                            }
                                        }

                                        Box (
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(MaterialTheme.colorScheme.primaryContainer),
                                            contentAlignment = Alignment.Center,
                                            ){
                                            Column (
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ){
                                                //overflow = TextOverflow.Ellipsis = Agrega "..." al final si el texto es demasiado largo para caber en una línea
                                                //maxLines = establece la cantidad de lineas permitas
                                                Text(
                                                    modifier = Modifier.padding(horizontal = 5.dp),
                                                    text = name,
                                                    maxLines = 3,
                                                    fontSize = 17.sp,
                                                    overflow = Ellipsis,
                                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                                )
                                                if(listProduct.detail.isNotEmpty()){
                                                    Text(
                                                        modifier = Modifier.padding(horizontal = 5.dp),
                                                        text = detail,
                                                        maxLines = 3,
                                                        fontSize = 17.sp,
                                                        overflow = Ellipsis,
                                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                                    )
                                                }

                                                Text(
                                                    modifier = Modifier.padding(horizontal = 5.dp),
                                                    text = "$$price",
                                                    maxLines = 1,
                                                    fontSize = 17.sp,
                                                    overflow = Ellipsis,
                                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                                )

                                                if(expanded){
                                                    Row (
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.SpaceEvenly
                                                    ){
                                                        ButtonHomeView(
                                                            text = stringResource(R.string.btn_delete),
                                                            onClick = {
                                                               showDialogExit = true
                                                            },
                                                            color = Color.Red
                                                        )
                                                        val notConnect = stringResource(R.string.not_conect_Internet)
                                                        if (showDialogExit) {
                                                            LogoutDialog(
                                                                title = stringResource(R.string.btn_delete),
                                                                text = stringResource(R.string.text_delete_confirm),
                                                                onClick = {
                                                                    if(status == ConnectivityObserver.Status.Lost){
                                                                        Toasty.info(context,notConnect,Toast.LENGTH_SHORT).show()
                                                                    }else{
                                                                        showDialogExit = false
                                                                        progressVisibleState.value = true
                                                                        viewModel.deleteProduct(id){succes->
                                                                            if(succes){
                                                                                progressVisibleState.value = false
                                                                            }else{
                                                                                progressVisibleState.value = false
                                                                            }
                                                                        }
                                                                    }
                                                                }, onDismiss = { showDialogExit = false })
                                                        }

                                                        ButtonHomeView(
                                                            text = stringResource(R.string.btn_Update),
                                                            onClick = {
                                                                if(status == ConnectivityObserver.Status.Lost){
                                                                    Toasty.info(context,notConnect,Toast.LENGTH_SHORT).show()
                                                                }else{
                                                                    //Codifica la url y que aseegura que no contenga caracteres especiales
                                                                    val encodedUrl = Uri.encode(image)
                                                                    val encodedId = Uri.encode(id)

                                                                    if(listProduct.detail.isEmpty()){
                                                                        detail = "null"
                                                                    }
                                                                    navigation.navigate("${Screen.AddProduct.route}/$encodedId/$name/$detail/$price/$encodedUrl")
                                                                }
                                                            },
                                                            color = Color.Blue
                                                        )
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else{
                    showScaffold = false
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                modifier = Modifier.padding(bottom = 30.dp),
                                fontSize = 20.sp,
                                text = stringResource(R.string.msj_not_data_null)
                            )
                            Image(
                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                                    .size(200.dp),
                                painter = painterResource(id = R.drawable.notdata),
                                contentDescription = "Not data"
                            )
                            val msjError = stringResource(R.string.not_conect_Internet)
                            Button(
                                onClick = {
                                    if(status == ConnectivityObserver.Status.Lost){
                                        Toasty.info(context,msjError,Toast.LENGTH_SHORT).show()
                                    }else{
                                        val res=null
                                        navigation.navigate("${Screen.AddProduct.route}/$res/$res/$res/$res/$res")
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            ) {
                                Text(stringResource(R.string.add_data),color = MaterialTheme.colorScheme.background)
                            }
                        }
                    }
                }
            }

            if (showDialog) {
                LogoutDialog(
                    title = stringResource(R.string.close_app),
                    text = stringResource(R.string.confirm_close_app),
                    onClick = {
                    viewModel.signOut()
                    navigation.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                    showDialog = false
                }, onDismiss = { showDialog = false })
            }

        }

        if(progressVisibleState.value){
            DialogProgress(msg = stringResource(R.string.msg_dialog_delete_data))
        }
    }
//}else{
  //  navigation.navigate(Screen.SignIn.route)
//}
}

/**
 * Muestra una imagen  a pantalla completa de la aplicacion, con opcion de cerrar
 *
 * @param imageUrl URl de la imagen que se mostrara
 *
 * @param navigation Instancia de NavController para la navegacion de pantallas
 */
@SuppressLint("SuspiciousIndentation")
@Composable
fun FullScreenImage(imageUrl : String, navigation : NavController) {
    val painter = rememberAsyncImagePainter(model = imageUrl)
    val state = painter.state
    var scale by remember{ mutableStateOf(1f) }
    var offset by remember{ mutableStateOf(Offset.Zero) }

    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black), contentAlignment = Alignment.Center
        ){
        val stateZoom = rememberTransformableState {zoomChange, panChange, rotationChange ->
            scale = (scale * zoomChange).coerceIn(1f,5f)
            val extraWith = (scale -1)*constraints.maxWidth
            val extraHeight = (scale -1)*constraints.maxHeight

            val maxX = extraWith / 2
            val maxY = extraHeight / 2

            offset = Offset(
                x = (offset.x + scale*panChange.x).coerceIn(-maxX,maxX),
                y = (offset.y + scale*panChange.y).coerceIn(-maxY,maxY)
            )
        }
        if(state is AsyncImagePainter.State.Loading){
            CircularProgressIndicator()
        }else if(state is AsyncImagePainter.State.Error) {
            Text(text = stringResource(id = R.string.error_full_image))
        }

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(stateZoom)
            ,
            contentScale = ContentScale.Fit
        )
        IconButton(onClick = { navigation.popBackStack() } , modifier = Modifier.align(Alignment.TopEnd)) {
            Image(
                painter = painterResource(id = R.drawable.close),
                contentDescription = "Exit Full Screen",
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
            )
        }
    }
}

/**
 * Composable para el estilo de boton, con un borde de color y un fondo blaco.
 *
 * @param text Texto que se mostrara en el boton
 *
 * @param onClick Se llamara cuando se haga clic en el boton
 *
 * @param color Color de borde del boton
 */
@Composable
fun ButtonHomeView(
    text : String,
    onClick: ()->Unit,
    color: Color
) {
    OutlinedButton(
        onClick = { onClick() },
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,//ancho del borde
            color = color
        )
    ) {
        Text(text = text,color = Color.Black)
    }
}

/**
 * Composable de inicio de sesion, con animacion Lottie miestras espera la autenticacion.
 *
 * @param viewModel Se utliza para gestionar la logica de inicio de sesion
 */
@SuppressLint("SuspiciousIndentation")
@Composable
fun Sing(
    viewModel : ProductViewModel
) {
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.planta))
    val progressLottie by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.progress))
    //Observador de conexion a internet
    val connectivityObserver = remember{ NetwokConnectivityObserver(context)}
    val status by connectivityObserver.observer().collectAsState(initial = ConnectivityObserver.Status.Unavailable)
    // Observa el usuario actual en el ViewModel


    val msjReturn = stringResource(R.string.msj_error_return)
    val msjNotInternet = stringResource(R.string.not_conect_Internet)

    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            if(!loading){
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
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.padding(10.dp),
                    onClick = {
                        if(status == ConnectivityObserver.Status.Lost || status == ConnectivityObserver.Status.Unavailable){
                            Toasty.info(context, msjNotInternet,Toast.LENGTH_SHORT).show()
                        }else{
                            loading = true
                            //Inicia sesion anonima
                            scope.launch {
                                try{
                                    viewModel.signInAnonymously()
                                    //val user = viewModel.signInAnonymously()
                                    /*if(user!=null){
                                        loading = false
                                        Log.e("Navigation", "${navigation.currentBackStackEntry?.destination?.route}")
                                        navigation.navigate(Screen.Home.route)

                                    }else{
                                        loading = false
                                        Toasty.error(context, msjReturn, Toast.LENGTH_SHORT).show()
                                        Log.e("SignIn", "User is null")
                                    }*/
                                }catch (e: Exception){
                                    loading = false
                                    Toasty.error(context,msjReturn,Toast.LENGTH_SHORT).show()
                                    e.printStackTrace()
                                }
                            }
                        }
                    }
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
            }else{
                //Animacion lottie en progreso cuando inicia session
                LottieAnimation(
                    modifier = Modifier.size(250.dp),
                    composition = progressLottie,
                    iterations = LottieConstants.IterateForever // La animacion se repite
                )
            }
        }
}
