package com.comye1.catcat.catbreed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CatBreedDetailScreen(viewModel: CatBreedViewModel, id: String?, navigateBack: () -> Boolean) {

    val catBreedItem = if (id != null) viewModel.getCatBreedItem(id) else null

    if (catBreedItem == null) {
        CatBreedDetailScaffold(title = "Not Found", navigateBack = { navigateBack() }) {

        }
    } else {
        CatBreedDetailScaffold(title = catBreedItem.name!!, navigateBack = { navigateBack() }) {
            Text(text = catBreedItem.description!!)
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