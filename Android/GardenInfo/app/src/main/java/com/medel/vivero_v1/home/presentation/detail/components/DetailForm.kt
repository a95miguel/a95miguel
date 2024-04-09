package com.medel.vivero_v1.home.presentation.detail.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.medel.vivero_v1.R
import com.medel.vivero_v1.home.presentation.detail.DetailEvent
import com.medel.vivero_v1.home.presentation.detail.DetailState
import es.dmoral.toasty.Toasty

/**
 * Muestra un formulario para agregar o actualizar un producto
 */
@Composable
fun DetailForm(
    state : DetailState,
    onEvent : (DetailEvent)->Unit
) {
    val context = LocalContext.current

    DetailTextfield(value = state.name, onValueChange = { onEvent(DetailEvent.NameChange(it))}, labelName = stringResource(R.string.et_name),errorMessage = state.nameError, keyboardTypeProvider = { KeyboardType.Text}, imeActionProvider = { ImeAction.Next})
    DetailTextfield(value = state.detail, onValueChange = { onEvent(DetailEvent.DetailChange(it))}, labelName = stringResource(R.string.et_detail),errorMessage = state.detailError, keyboardTypeProvider = { KeyboardType.Text}, imeActionProvider = { ImeAction.Next})
    DetailTextfield(value = state.price, onValueChange = { onEvent(DetailEvent.PriceChange(it)) }, labelName = stringResource(R.string.et_price),errorMessage = state.priceError, keyboardTypeProvider = { KeyboardType.Number}, imeActionProvider = { ImeAction.Go})
    val msjError = stringResource(R.string.erro_toast_select_image)

    if(state.isLoading){
        DialogProgress(msg = stringResource(R.string.msg_dialog_add_data))
    }

    if(state.isLoadingUpdate){
        DialogProgress(msg = stringResource(R.string.msg_dialog_update_data))
    }

    if(state.id.isNullOrEmpty()){
        DetailButton(text = stringResource(R.string.btn_add)) {
            if(state.imageUrl == null){
                Toasty.error(context,msjError, Toast.LENGTH_SHORT).show()
            }else{
                onEvent(DetailEvent.DetailSave)
            }
        }
    }else{
        DetailButton(text = stringResource(R.string.btn_Update)) {
            if(state.imageUrl ==null){
                Toasty.error(context,msjError, Toast.LENGTH_SHORT).show()
            }else{
                onEvent(DetailEvent.DetailUpdate)
            }

        }
    }



    // Mostrar un mensaje de error si ocurrió un error durante la operación
    if(state.msgError!=null){
        Toasty.error(context,state.msgError,Toast.LENGTH_SHORT).show()
    }

}