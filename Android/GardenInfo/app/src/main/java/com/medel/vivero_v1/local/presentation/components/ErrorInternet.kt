package com.medel.vivero_v1.local.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.medel.vivero_v1.R
import kotlinx.coroutines.delay


/**
 * Muuetra un mesaje con una imagen. Esperandon unos  segundos para el cambio de pantalla
 */
@Composable
fun ErrorInternet(
    onHomeLocal :()->Unit
) {
    NotAccessMsg(text = stringResource(R.string.not_conect_Internet), imagePainter = painterResource(id = R.drawable.notinternet))
    LaunchedEffect(Unit){
       delay(2500)
        onHomeLocal()
    }
}