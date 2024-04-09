package com.medel.vivero_v1.home.presentation.detail

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medel.vivero_v1.R
import com.medel.vivero_v1.home.presentation.detail.presentation.DetailViewModel
import com.medel.vivero_v1.home.presentation.detail.components.DetailForm
import com.medel.vivero_v1.home.presentation.detail.components.ImageView

/**
 * Composable que muestra la pantalla de detalles
 *
 * @param onBack retorna a pantalla anterior
 * @param onCreateBack retorna a la pantala de Homr
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onBack : ()->Unit,
    onCreateBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state  = viewModel.state

    LaunchedEffect(state.isCreated){
        if(state.isCreated){
            onCreateBack()
        }
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if(state.id.isNullOrEmpty()){
                        Text(
                            text = stringResource(R.string.add_data),
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }else{
                        Text(
                            text = stringResource(R.string.update_data),
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",tint = MaterialTheme.colorScheme.onTertiaryContainer)
                }
            }
            )
        }
    ){
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                ImageView(
                    urlImage = state.imageUrl.toString(),
                    onEvent = viewModel::onEvent
                )
            }

            item {
                DetailForm(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }


        }
    }
}