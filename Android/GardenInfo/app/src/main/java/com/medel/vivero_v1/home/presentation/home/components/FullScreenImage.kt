package com.medel.vivero_v1.home.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.medel.vivero_v1.R

/**
 * Muestra la imagen en pantalla completa con la capacidad de hacer zoom
 *
 * @param onBack retorna a la pantalla anteriror
 * @param imageUrl Url de la imagen a mostrar
 */
@Composable
fun FullScreenImage(
    onBack: () -> Unit,
    imageUrl : String
) {
    val painter = rememberAsyncImagePainter(model = imageUrl)
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = painter.state

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        val stateZoom = rememberTransformableState { zoomChange, panChange, rotationChange ->
            scale = (scale * zoomChange).coerceIn(1f, 5f)
            val extraWith = (scale - 1) * constraints.maxWidth
            val extraHeight = (scale - 1) * constraints.maxHeight

            val maxX = extraWith / 2
            val maxY = extraHeight / 2

            offset = Offset(
                x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY)
            )
        }

        //Muestra circular si la imagen esta en Loading
        if(state is AsyncImagePainter.State.Loading){
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .size(30.dp),
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }else if(state is AsyncImagePainter.State.Error) {
            Text(text = stringResource(id = R.string.error_full_image))
        }

        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(stateZoom),
            contentScale = ContentScale.Fit
        )

        //Boton para salir de la pantalla
        IconButton(onClick = onBack, modifier = Modifier.align(Alignment.TopEnd)) {
            Image(
                painter = painterResource(id = R.drawable.close),
                contentDescription = "Exit fullscreen",
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
            )
        }
    }
}