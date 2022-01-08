package com.comye1.catcat.catbreed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CatBreedDetailScreen(viewModel: CatBreedViewModel, id: String, navigateBack: () -> Boolean) {

    val catBreedItem = if (id != "") viewModel.getCatBreedItem(id) else null

    if (catBreedItem == null) {
        CatBreedDetailScaffold(title = "Not Found", navigateBack = { navigateBack() }) {

        }
    } else {
        /*
        id에 해당하는 Breed 이미지
         */
        val catImages = rememberSaveable {
            mutableStateOf(viewModel.catImages)
        }
//        viewModel.getCatImagesById(id)
        CatBreedDetailScaffold(title = catBreedItem.name!!, navigateBack = { navigateBack() }) {
            Text(text = catBreedItem.description!!)
            /*
            이미지 개수, url 출력
             */
            Text(text = "${catImages.value.size} images")
            viewModel.catImages.forEach {
                Text(text = it.url)
            }
        }

    }
}

@Composable
fun CatBreedDetailScaffold(
    title: String,
    navigateBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                },
            )
        }
    ) {
        Column(Modifier.padding(16.dp)) {
            content()
        }
    }
}