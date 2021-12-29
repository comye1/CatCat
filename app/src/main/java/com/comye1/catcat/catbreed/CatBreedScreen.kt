package com.comye1.catcat.catbreed

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CatBreedScreen(viewModel: CatBreedViewModel) {
    Column() {
        viewModel.catBreeds.forEach {
            Card {
                Text(text = it.toString())
            }
        }
    }
}