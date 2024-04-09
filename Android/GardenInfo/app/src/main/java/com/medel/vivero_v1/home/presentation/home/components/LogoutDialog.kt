package com.medel.vivero_v1.home.presentation.home.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.medel.vivero_v1.R

/**
 * Muestra un Dialogo para confirmar la salida de la aplicacion
 */
@Composable
fun LogoutDialog(
    title : String,
    text : String,
    onClick : ()-> Unit,
    onDismiss : ()->Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title,color = MaterialTheme.colorScheme.onTertiaryContainer) },
        text = { Text(text,color = MaterialTheme.colorScheme.onTertiaryContainer) },
        confirmButton = {
            Button(onClick =  onClick , colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)) {
                Text(stringResource(R.string.btn_confirm),color = MaterialTheme.colorScheme.background)
            }
        },
        dismissButton = {
            Button(onClick = onDismiss , colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)) {
                Text(stringResource(R.string.btn_cancel),color = MaterialTheme.colorScheme.background)
            }
        }
    )
}