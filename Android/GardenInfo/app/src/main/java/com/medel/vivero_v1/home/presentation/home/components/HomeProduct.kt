package com.medel.vivero_v1.home.presentation.home.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.medel.vivero_v1.R
import com.medel.vivero_v1.home.domain.models.Product
import com.medel.vivero_v1.home.presentation.detail.components.DialogProgress
import com.medel.vivero_v1.home.presentation.home.HomeEvent
import com.medel.vivero_v1.home.presentation.home.HomeState
import es.dmoral.toasty.Toasty

/**
 * Muestra el listado del producto en la pantalla principal
 */
@Composable
fun HomeProduct(
    onEvent : (HomeEvent) -> Unit,
    state : HomeState,
    product: Product,
    textSearch: String,
    expanded: Boolean,
    onFullImage: ()->Unit,
    onClickUpdate : () -> Unit
) {
    val context = LocalContext.current
    if (product.name.contains(textSearch, ignoreCase = true) || product.detail.contains(textSearch, ignoreCase = true)){
        val painter = rememberAsyncImagePainter(model = product.imageUrl)
        val stateImage = painter.state
        var expandedIcon by remember{ mutableStateOf(expanded) }

        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Box(modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()){
                //Muestra circular miestra la se carga la imagen
                if (stateImage is AsyncImagePainter.State.Loading){
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .align(
                                Alignment.Center
                            )
                            .size(30.dp),
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                }
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                //Boton para expander y contraer. Muestra dos botones (Eliminar y Actualizar)
                IconButton(
                    onClick = { expandedIcon =! expandedIcon},
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter =if(expandedIcon) painterResource(id = R.drawable.expand_less) else painterResource(id = R.drawable.expand_more),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                //Boton para ver la imagen en pantalla completa
                IconButton(
                    onClick = { onFullImage() },
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.fullscreen),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ){
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    //overflow = TextOverflow.Ellipsis = Agrega "..." al final si el texto es demasiado largo para caber en una línea
                    //maxLines = establece la cantidad de lineas permitas
                    Text(
                        text = product.name,
                        modifier = Modifier.padding(horizontal = 5.dp),
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    if (product.detail.isNotEmpty()){
                        Text(
                            text = product.detail,
                            modifier = Modifier.padding(horizontal = 5.dp),
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "$"+product.price,
                        modifier = Modifier.padding(horizontal = 5.dp),
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    if(expandedIcon){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            OutlinedButton(
                                onClick = { onEvent(HomeEvent.ShowDialogDelete(true)) },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White
                                ),
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = Color.Red
                                )
                            ) {
                                Text(text = stringResource(R.string.btn_delete), color = Color.Black, fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = { onClickUpdate() },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White
                                ),
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = Color.Blue
                                )
                            ) {
                                Text(text = stringResource(R.string.btn_Update), color = Color.Black, fontSize = 12.sp)
                            }
                        }

                        //Muestra Dialogo para confirmar la eliminacion
                        if (state.showDialogDelete){
                            LogoutDialog(
                                title = stringResource(R.string.btn_delete),
                                text = stringResource(R.string.text_delete_confirm),
                                onClick = {
                                    onEvent(HomeEvent.DeleteChange(product.id))
                                }
                            ) {
                                onEvent(HomeEvent.DismissDialog(false))
                            }
                        }

                    }
                }
            }

        }


    }


    if(state.isLoadingDelete){
        DialogProgress(msg = stringResource(R.string.msg_dialog_delete_data))
    }

    if(state.msgError!=null){
        Toasty.error(context,state.msgError, Toast.LENGTH_SHORT).show()
    }

}