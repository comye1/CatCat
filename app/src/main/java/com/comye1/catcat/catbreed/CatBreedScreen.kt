package com.comye1.catcat.catbreed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.comye1.catcat.R

@Composable
fun CatBreedScreen(viewModel: CatBreedViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Cat Breeds", style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            viewModel.catBreeds.forEach {
                if (it.name != null) {
                    item {
                        Card(
                            backgroundColor = Color(229, 204, 255),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.h5,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                it.image?.url?.let { url ->
                                    Image(
                                        painter = rememberImagePainter(
                                            data = url,
                                            builder = {
                                                allowHardware(false)
                                                size(400)
                                            },
                                        ),
                                        contentDescription = "breed image",
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                it.description?.let { text ->
                                    Text(text = text, style = MaterialTheme.typography.h6)
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                }
            }
        }

    }
}