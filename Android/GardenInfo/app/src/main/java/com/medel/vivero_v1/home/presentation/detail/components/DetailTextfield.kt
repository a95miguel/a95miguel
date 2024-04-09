package com.medel.vivero_v1.home.presentation.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.medel.vivero_v1.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DetailTextfield(
    value: String,
    onValueChange: (String) -> Unit,
    labelName : String,
    errorMessage: String? = null,
    keyboardTypeProvider: () -> KeyboardType = { KeyboardType.Text },
    imeActionProvider: () -> ImeAction = { ImeAction.Next },
) {
    val keyboardType = keyboardTypeProvider()
    val imeAction = imeActionProvider()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 15.dp),
            label = { Text(text = labelName)},
            maxLines = 1 , singleLine = true,
            trailingIcon = {
                if (value.isNotEmpty()){
                    Icon(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "Cancel",
                        modifier = Modifier.clickable {
                            onValueChange("")
                        }
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                cursorColor = MaterialTheme.colorScheme.surfaceTint,
                focusedLabelColor = MaterialTheme.colorScheme.surfaceTint
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,

            ),
            //Oculta el teclado solo cuando tenga ImeAction.go
            keyboardActions = KeyboardActions(
                onGo = {
                    if(imeActionProvider() == ImeAction.Go){
                        keyboardController?.hide()
                    }
                }
            )
        )

        if(errorMessage!=null){
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = Color.Red)
        }
    }
}