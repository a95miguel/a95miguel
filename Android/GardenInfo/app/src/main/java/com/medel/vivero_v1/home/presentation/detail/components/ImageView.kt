package com.medel.vivero_v1.home.presentation.detail.components


import android.Manifest
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.medel.vivero_v1.R
import com.medel.vivero_v1.home.presentation.detail.DetailEvent
import es.dmoral.toasty.Toasty

/**
 * Muestra la imagen y selecciona una imagen desde galeria
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageView(
    urlImage : String,
    onEvent : (DetailEvent)->Unit
) {

    val context = LocalContext.current
    var  currentImage = Uri.parse(urlImage)

    val painter = currentImage?.let {uri->
        rememberAsyncImagePainter(model = uri)
    }?:painterResource(id = R.drawable.flowers)

    //Estado para manejar los permisos
    val permissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(permission = Manifest.permission.READ_MEDIA_IMAGES)
    } else{
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    }


    //Lanzador de la actividad para obtener el contenido de la imagen
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){ uri : Uri?->
        uri?.let {
            currentImage = it
            onEvent(DetailEvent.ImageUrlChange(it))
        }
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (image1, image2) = createRefs()
        Image(
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(180.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    shape = CircleShape
                )
        )

        Image(
            painter = painterResource(id = R.drawable.photo_camera),
            contentDescription = "Photo camera",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background),
            modifier = Modifier
                .constrainAs(image2) {
                    top.linkTo(image1.top, margin = 130.dp)
                    start.linkTo(parent.start, margin = 110.dp)
                    end.linkTo(parent.end)
                }
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                .size(50.dp)
                .padding(10.dp)
                .clickable {
                    when (permissionState.status) {
                        is PermissionStatus.Denied -> {
                            if (permissionState.status.shouldShowRationale) {
                                permissionState.launchPermissionRequest()
                                Toasty
                                    .info(
                                        context,
                                        R.string.confirm_permission_two,
                                        Toast.LENGTH_LONG
                                    )
                                    .show()

                            } else {
                                permissionState.launchPermissionRequest()
                            }
                        }

                        PermissionStatus.Granted -> {
                            launcher.launch("image/*")
                        }
                    }
                }
        )
    }
}

