package com.medel.vivero_v1.local.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Muestra un mensaje de error con un imagen
 */
@Composable
fun NotAccessMsg(
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
