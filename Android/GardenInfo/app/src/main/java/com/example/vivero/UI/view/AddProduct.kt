package com.example.vivero.UI.view

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vivero.R
import com.example.vivero.domain.model.Productos
import com.example.vivero.network.ConnectivityObserver
import com.example.vivero.network.NetwokConnectivityObserver
import com.example.vivero.UI.viewModel.ProductViewModel
import es.dmoral.toasty.Toasty

/**
 * Agrega o actualiza elementos de los productos
 *
 * @param navigation Controlador de navegacion para cambiar a otra pantalla
 *
 * @param viewModel proporciona los datos y la logica de la aplicaion
 *
 * @param product Representa los datos que seran utilizados
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun AddProduct(
    navigation : NavController,
    viewModel : ProductViewModel,
    product: Productos
    ) {
    val name = remember { mutableStateOf(TextFieldValue(product.name)) }
    val detail = remember { mutableStateOf(TextFieldValue(product.detail)) }
    val price = remember { mutableStateOf(TextFieldValue(product.price)) }
    var showMsgError  by remember{ mutableStateOf("") }
    var showMsgErrorDetail  by remember{ mutableStateOf("") }
    var showMsgErrorPrice  by remember{ mutableStateOf("") }
    var image by remember { mutableStateOf(Uri.parse(product.imageUrl)) }
    var msgDialogProgress by remember{ mutableStateOf("") }
    val context = LocalContext.current
    val connectivityObserver = remember{ NetwokConnectivityObserver(context) }
    val status by connectivityObserver.observer().collectAsState(initial = ConnectivityObserver.Status.Unavailable)
    val progressVisibleState = remember { mutableStateOf(false) }

    if(product.id == "null"){
        name.value = TextFieldValue("")
        detail.value = TextFieldValue("")
        price.value = TextFieldValue("")
        image = null
    }

    if(product.detail == "null"){
        detail.value = TextFieldValue("")
    }

    Scaffold(
        topBar ={
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ),
               title = {
                   if(product.id == "null"){
                       Text(text = stringResource(R.string.add_data), fontSize = 18.sp, color = MaterialTheme.colorScheme.onTertiaryContainer)
                   }else{
                       Text(text = stringResource(R.string.update_data), fontSize = 18.sp, color = MaterialTheme.colorScheme.onTertiaryContainer)
                   }
               },
               navigationIcon = {
                   IconButton(onClick = { navigation.popBackStack() }) {
                       Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",tint = MaterialTheme.colorScheme.onTertiaryContainer)
                   }
               }

           )
        }
    ) { innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.onPrimary)
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                item {
                    ImageView(
                        urlImage = image,
                        onImageSelected = { selected ->
                            image = selected
                        }
                    )

                    TextFieldView(state = name, text = stringResource(R.string.et_name),keyboardTypeProvider = { KeyboardType.Text}, imeActionProvider = { ImeAction.Next},showMsgError)
                    TextFieldView(state = detail, text = stringResource(R.string.et_detail),keyboardTypeProvider = { KeyboardType.Text}, imeActionProvider = { ImeAction.Next},showMsgErrorDetail)
                    TextFieldView(state = price, text = stringResource(R.string.et_price),keyboardTypeProvider = { KeyboardType.Number}, imeActionProvider = { ImeAction.Go},showMsgErrorPrice)

                    //Valida los datos ingresados
                    val isValidPrice = price.value.text.matches(Regex("^[0-9]+([.][0-9]{2})?\$"))
                    val isValidName = name.value.text.matches(Regex("^[\\p{L}\\s]+\$"))
                    val isValidDetail = detail.value.text.matches(Regex("^[\\p{L}0-9\\s]*$"))
                    //\p{L} se utiliza para representar cualquier caracter de letra, incluye acentos y la letra Ñ
                    // \\Permite los espacio

                    //Muestra el mensaje de error que se le envia al campo de texto
                    val msjError = stringResource(R.string.erro_toast_select_image)
                    val msjAddName = stringResource(R.string.error_msj_add_name)
                    val msjNotNumber = stringResource(R.string.msj_error_not_number)
                    val msjNotCaracte = stringResource(R.string.msj_error_not_caracte)
                    val msjAddPrice = stringResource(R.string.msj_error_add_price)
                    val msjValidPrice = stringResource(R.string.msj_error_valid_price)
                    val notConnect = stringResource(R.string.not_conect_Internet)
                    val dialogAddData = stringResource(R.string.msg_dialog_add_data)
                    val dialogUpdateData = stringResource(R.string.msg_dialog_update_data)
                    val msjreturn = stringResource(R.string.msj_error_return)

                    if(product.id == "null"){
                        ButtonView(stringResource(R.string.btn_add), onClick = {
                            if(image == null){
                                Toasty.error(context,msjError,Toast.LENGTH_SHORT).show()
                            }else{
                                when{
                                    name.value.text.isEmpty() -> showMsgError = msjAddName
                                    !isValidName -> showMsgError = msjNotNumber
                                    !isValidDetail -> showMsgErrorDetail = msjNotCaracte
                                    price.value.text.isEmpty() -> showMsgErrorPrice = msjAddPrice
                                    !isValidPrice -> showMsgErrorPrice = msjValidPrice

                                    else ->{
                                        if(status == ConnectivityObserver.Status.Lost){
                                            progressVisibleState.value = false
                                            Toasty.info(context,notConnect,Toast.LENGTH_SHORT).show()
                                        }else{
                                            progressVisibleState.value = true
                                            msgDialogProgress = dialogAddData                                     

                                            viewModel.insertProduct(Productos("",name.value.text,detail.value.text,price.value.text,image.toString())) { success->
                                                if(success){
                                                    progressVisibleState.value = false
                                                    navigation.popBackStack()
                                                }else{
                                                    progressVisibleState.value = false
                                                    Toasty.error(context,msjreturn,Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        })
                    }else{
                        ButtonView(text = stringResource(R.string.btn_Update), onClick = {
                            if(image == null){
                                Toasty.error(context,msjError,Toast.LENGTH_SHORT).show()
                            }else{
                                when{
                                    name.value.text.isEmpty() -> showMsgError = msjAddName
                                    !isValidName -> showMsgError = msjNotNumber
                                    !isValidDetail -> showMsgErrorDetail = msjNotCaracte
                                    price.value.text.isEmpty() -> showMsgErrorPrice = msjAddPrice
                                    !isValidPrice -> showMsgErrorPrice = msjValidPrice
                                    else ->{
                                        if(status == ConnectivityObserver.Status.Lost){
                                            progressVisibleState.value = false
                                            Toasty.info(context,notConnect,Toast.LENGTH_SHORT).show()
                                        }else{
                                        progressVisibleState.value = true
                                        msgDialogProgress = dialogUpdateData

                                        //Valida si la imagena a cambiado
                                        val originalImageUri = Uri.parse(product.imageUrl)
                                        val isImageChanged = image != originalImageUri
                                        if(isImageChanged){
                                            viewModel.updateImage(Productos(product.id,name.value.text,detail.value.text,price.value.text,image.toString())){ succes->
                                                if(succes){
                                                    progressVisibleState.value = false
                                                    navigation.popBackStack()
                                                }else{
                                                    progressVisibleState.value = false
                                                    Toasty.error(context,msjreturn,Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        }else{
                                            viewModel.updatePoduct(Productos(product.id,name.value.text,detail.value.text,price.value.text,image.toString())) { succes->
                                                if(succes){
                                                    progressVisibleState.value = false
                                                    navigation.popBackStack()
                                                }else{
                                                    progressVisibleState.value = false
                                                    Toasty.error(context,msjreturn,Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        }
                                        }

                                    }
                                }
                            }
                        })
                    }
                }
            }
        }

        if(progressVisibleState.value){
            DialogProgress(msg = msgDialogProgress)
        }

    }
}


