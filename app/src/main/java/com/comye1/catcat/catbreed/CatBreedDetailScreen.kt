package com.comye1.catcat.catbreed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
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
        val catImages = viewModel.catImages.observeAsState()
//        viewModel.getCatImagesById(id)
        CatBreedDetailScaffold(title = catBreedItem.name!!, navigateBack = { navigateBack() }) {
            Text(text = catBreedItem.description!!)
            /*
            이미지 pager에 출력
             */
//            catImages.value?.forEach {
//                Text(text = it.url)
//            }
            catImages.value?.let {
                val pagerState = rememberPagerState()
                HorizontalPager(
                    count = it.size, state = pagerState, modifier = Modifier.fillMaxHeight(.8f)
                ) { page ->
                    Image(
                        painter = rememberImagePainter(
                            data = it[page].url
                        ),
                        contentDescription = "cat image",
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        activeColor = MaterialTheme.colors.primaryVariant,
                    )
                }
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