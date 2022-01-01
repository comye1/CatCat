package com.comye1.catcat.catbreed

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CatBreedDetailScreen(viewModel: CatBreedViewModel, id: String?, navigateBack: () -> Boolean) {
    id?.let {
        Text(text = viewModel.getCatBreedItem(id).toString())
    }
}