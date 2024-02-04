package com.example.vivero.UI.view

import android.Manifest.*
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.example.vivero.R
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import es.dmoral.toasty.Toasty

/**
 * Composable para mostrar y seleccionar imagen. Se usa ConstraintLayout para las pocisiones de
 * de los circulos de la imagenes. El usuario selecciona una nueva imagen de la galeria.
 *
 * @param urlImage La URI de la imagen seleccionada
 *
 * @param onImageSelected Lambda que se llama cuando selecciona una nueva imagen
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageView(
    urlImage: Uri?,
    onImageSelected : (Uri)-> Unit
) {
    //Estado para manejar los permisos
    val permissionState = rememberPermissionState(permission = permission.READ_EXTERNAL_STORAGE)
    val context = LocalContext.current
    //Obtine  la imagen actual
    var currentImage by  remember { mutableStateOf(urlImage) }

    val painter = currentImage?.let {uri->
        rememberAsyncImagePainter(model = uri)
    }?:painterResource(id = R.drawable.flowers)

    //Lanzador de la actividad para obtener el contenido de la imagen
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){ uri : Uri?->
        uri?.let {
            currentImage = it
            onImageSelected(it)
        }
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (image1, image2) = createRefs()

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(180.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    shape = CircleShape
                )
        )

        val confirmPermi = stringResource(R.string.confirm_permission)

        Image(
            painter = painterResource(id = R.drawable.photo_camera),
            contentDescription = "Photo camera",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background),
            modifier = Modifier
                .constrainAs(image2) {
                    top.linkTo(image1.top, margin = 130.dp)
                    start.linkTo(parent.start, margin = 110.dp)
                    end.linkTo(parent.end)
                }
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                .size(50.dp)
                .padding(10.dp)
                .clickable {
                    permissionState.let {
                        //Valido la version de android es superios a 13,para determinar si utliza "Permisos de solo un uso"
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            //No necesita los permisos, por que es de solo un uso
                            launcher.launch("image/*")
                        } else {
                            if (it.status.isGranted) {
                                launcher.launch("image/*")
                            } else if (it.status.shouldShowRationale) {
                                Toasty
                                    .info(
                                        context, confirmPermi,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            } else {
                                // Si los permisos no están otorgados, solicitamos permisos
                                it.launchPermissionRequest()
                            }
                        }
                    }
                }
        )
    }
}

/**
 * Composable para campo de texto personalizado, como el tipo de entrada, mostrar icono en la cancelacion
 * cuando hay texto ingresado.
 *
 * @param state Estado mutable que contiene el valor del texto
 * @param text Texto de la etiqueta para el campo de texto
 * @param keyboardTypeProvider tipo de teclado para el campo de texto
 * @param imeActionProvider Proporciona la accion del IME
 * @param msgError El mensaje de error que se muestra abajo del campo de texto
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldView(
    state : MutableState<TextFieldValue>,
    text : String,
    keyboardTypeProvider: () -> KeyboardType = { KeyboardType.Text },
    imeActionProvider: () -> ImeAction = { ImeAction.Next },
    msgError : String
) {
    val keyboardType = keyboardTypeProvider()
    val imeAction = imeActionProvider()
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 15.dp),
        value = state.value,
        onValueChange ={ state.value = it},
        label = { Text(text = text)},
        maxLines = 1, singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        //Oculta el teclado solo cuando tenga ImeAction.go
        keyboardActions = KeyboardActions(
            onGo = {
                if(imeActionProvider() == ImeAction.Go){
                    keyboardController?.hide()
                }
            }
        ),
        trailingIcon = {
            //Muestra el icono cuando el campo de texto no este vacio
            if(state.value.text.isNotBlank()){
                Icon(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "Cancel",
                    modifier = Modifier.clickable {
                        state.value = TextFieldValue("")
                    }
                )

            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.surfaceTint,
            focusedLabelColor = MaterialTheme.colorScheme.surfaceTint
        )
    )
    Text(text = msgError,color = MaterialTheme.colorScheme.error)
}

/**
 * Composable para un boton elevado personalozado
 *
 * @param text Texto que se mostrara en el boton
 *
 * @param onClick Se llamara cuando se haga clic en el boton
 */
@Composable
fun ButtonView(
    text: String,
    onClick : ()->Unit
) {
    ElevatedButton(
        onClick = { onClick() },
        modifier = Modifier.padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(text = text,fontSize = 18.sp,color = MaterialTheme.colorScheme.background)
    }
}


//@Preview(showBackground = true)
/**
 * Composable para mostrar un dialogo  de progreso con una animacion de carga.
 * La animacion de carga se base en un archivo Lottie
 *
 * @param msg El mensaje que se mostrara
 */
@Composable
fun DialogProgress(
    msg : String
) {
    //Obtiene la animacion de Lottie desde el archivo Raw
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Card {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background( MaterialTheme.colorScheme.onPrimary)
                    .fillMaxWidth()
            ){
                Text(
                    text = msg,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                LottieAnimation(
                    modifier = Modifier
                        .size(250.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever // La animacion se repite
                )

            }
        }
    }
}

/**
 * Composable para un campo de busqueda con opciones de personalizacion.
 *
 * @param state El estado que mantiene el valor del campo de busqueda
 */
@Composable
fun SearchView(
    state: MutableState<TextFieldValue>
) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, bottom = 2.dp, end = 20.dp),
            value = state.value,
            onValueChange = { state.value = it },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search",
                    modifier = Modifier.size(25.dp)
                )
            },
            placeholder = {
                Text(text = stringResource(R.string.text_seach))
            },
            maxLines = 1, singleLine = true,
            trailingIcon = {
                if(state.value.text.isNotBlank()){
                    Icon(painter = painterResource(id = R.drawable.cancel), contentDescription = "Cancel",modifier = Modifier.clickable {
                        state.value = TextFieldValue("")
                    })

                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.surfaceTint,
                focusedLabelColor = MaterialTheme.colorScheme.surfaceTint
            )
        )
}

/**
 * Composable para mostrar un dialogo de confimacion para el cierre de la aplicacion y eliminacion de productos
 *
 * @param title El titulo del dialogo
 * @param text El texto del dialogo que describe la accion a realizar
 * @param onClick Se llamara cuando la accion se confirme
 * @param onDismiss Se llamara cuando la accion se cancele
 */
@Composable
fun LogoutDialog(title:String,text: String,onClick: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title,color = MaterialTheme.colorScheme.onTertiaryContainer) },
        text = { Text(text,color = MaterialTheme.colorScheme.onTertiaryContainer) },
        confirmButton = {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text(stringResource(R.string.btn_confirm),color = MaterialTheme.colorScheme.background)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text(stringResource(R.string.btn_cancel),color = MaterialTheme.colorScheme.background)
            }
        }
    )
}