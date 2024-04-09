package com.medel.vivero_v1.home.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.medel.vivero_v1.R

@Composable
fun DialogProgress(
    msg : String
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Card {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background( MaterialTheme.colorScheme.onPrimary)
                    .fillMaxWidth()
            ){
                Text(
                    text = msg,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                LottieAnimation(
                    modifier = Modifier
                        .size(250.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever // La animacion se repite
                )

            }
        }
    }
}
