package com.medel.vivero_v1.home.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.medel.vivero_v1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    value: String,
    onValueChange: (String) -> Unit
    ) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp, end = 20.dp),
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
            if(value.isNotEmpty()){
                Icon(painter = painterResource(id = R.drawable.cancel), contentDescription = "Cancel",modifier = Modifier.clickable {
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
        )

    )
}