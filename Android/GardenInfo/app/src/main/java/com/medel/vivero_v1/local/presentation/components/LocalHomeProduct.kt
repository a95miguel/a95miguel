package com.medel.vivero_v1.local.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medel.vivero_v1.home.data.local.entity.ProductEntity

/**
 * Muestra el contenido de la lista de los datos almacenados en local, de la pantalla pricipal.
 */
@Composable
fun LocalHomeProduct(
    product: ProductEntity,
    textSearch: String
) {
    if(product.name.contains(textSearch,ignoreCase = true) || product.detail.contains(textSearch,ignoreCase = true)){
        OutlinedCard (
            modifier = Modifier.padding(13.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            border = BorderStroke(1.dp,MaterialTheme.colorScheme.onBackground),
            shape = RoundedCornerShape(10.dp)
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = product.name,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(5.dp))

                if (product.detail.isNotEmpty()){
                    Text(
                        text = product.detail,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "$"+product.price,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 13.sp
                )

            }
        }
    }

}