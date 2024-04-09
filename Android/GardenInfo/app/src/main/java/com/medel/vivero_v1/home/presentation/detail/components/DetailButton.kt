package com.medel.vivero_v1.home.presentation.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vivero.network.ConnectivityObserver
import com.example.vivero.network.NetwokConnectivityObserver
import com.medel.vivero_v1.R

@Composable
fun DetailButton(
    text : String,
    onClick : ()->Unit
) {
    val context = LocalContext.current
    val connectivityObserver = remember{ NetwokConnectivityObserver(context) }
    val status by connectivityObserver.observer().collectAsState(initial = ConnectivityObserver.Status.Unavailable)
    val notConnect = stringResource(R.string.not_conect_Internet)
    var enable = !(status == ConnectivityObserver.Status.Lost || status == ConnectivityObserver.Status.Limited || status == ConnectivityObserver.Status.Unavailable)
    ElevatedButton(
        onClick = onClick,
        modifier = Modifier.padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        enabled = enable
    ) {
        Text(text = text,fontSize = 18.sp,color = MaterialTheme.colorScheme.background)
    }

    if(!enable){
        Text(text = notConnect)
    }
}