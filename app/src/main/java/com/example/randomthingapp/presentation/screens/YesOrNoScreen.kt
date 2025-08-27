package com.example.randomthingapp.presentation.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.randomthingapp.presentation.viewmodels.YesNoViewModel
import com.example.randomthingapp.presentation.viewmodels.capitalizeFirstChar

@Composable
fun YesOrNoScreen(modifier: Modifier, viewModel: YesNoViewModel) {
    val answer by viewModel.answer.observeAsState()

    val gifEnabledLoader = ImageLoader.Builder(LocalContext.current).components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Si o No")

        Button(onClick = { viewModel.fetchAnswer() }) {
            Text("Tirar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        answer?.let {
            var isLoading by remember { mutableStateOf(true) }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = if (isLoading) "Cargando..." else it.answer.capitalizeFirstChar())
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(it.image)
                        .crossfade(true).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    imageLoader = gifEnabledLoader,
                    onLoading = { isLoading = true },
                    onSuccess = { isLoading = false },
                    onError = { isLoading = false })

                if (isLoading) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
