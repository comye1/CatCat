package com.comye1.catcat.catbreed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.comye1.catcat.catfact.LoadingAnimation
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
        CatBreedDetailScaffold(title = catBreedItem.name!!, navigateBack = { navigateBack() }) {
            /*
            이미지 pager에 출력
             */
//            catImages.value?.forEach {
//                Text(text = it.url)
//            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (catImages.value == null) {
                    LoadingAnimation()
                }
                catImages.value?.let {
                    val pagerState = rememberPagerState()
                    HorizontalPager(
                        count = it.size,
                        state = pagerState,
                        modifier = Modifier.fillMaxHeight(.8f)
                    ) { page ->
                        Image(
                            painter = rememberImagePainter(
                                data = it[page].url
                            ),
                            contentDescription = "cat image",
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        activeColor = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            LazyColumn {
                item {
                    Text(text = catBreedItem.description!!)
                }
                item {
                    Text(text = catBreedItem.description!!)
                }
                item {
                    Text(text = catBreedItem.description!!)
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