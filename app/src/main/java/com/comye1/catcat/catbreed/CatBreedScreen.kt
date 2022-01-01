package com.comye1.catcat.catbreed

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.comye1.catcat.catbreed.models.BreedItem
import com.comye1.catcat.utils.CatScreen

@Composable
fun CatBreedScreen(
    viewModel: CatBreedViewModel,
    toDetailScreen: (String) -> Unit
) {

    val catBreedList = viewModel.catBreeds

    val (query, setQuery) = remember {
        mutableStateOf("")
    }

    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = query.isEmpty()) {
        scrollState.animateScrollToItem(0)
    }

    CatScreen(title = "Cat Breeds") {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CatBreedSearchBar(query, setQuery)
        }
        Spacer(modifier = Modifier.height(8.dp))
        CatBreedList(
            list = catBreedList.filter {
                it.name != null && it.name!!.lowercase().contains(query.lowercase())
            },
            scrollState = scrollState,
            onCatBreedItemClick = { id ->
                toDetailScreen(id)
            }
        )
    }
}

@Composable
fun CatBreedSearchBar(query: String, setQuery: (String) -> Unit) {

    OutlinedTextField(
        value = query,
        onValueChange = setQuery,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primaryVariant,
            unfocusedBorderColor = MaterialTheme.colors.primaryVariant
        ),
        shape = CircleShape,
        trailingIcon = {
            IconButton(onClick = { setQuery("") }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear"
                )
            }
        }
    )
}

@Composable
fun CatBreedList(
    list: List<BreedItem>,
    scrollState: LazyListState,
    onCatBreedItemClick: (String) -> Unit
) {
    LazyColumn(state = scrollState) {
        list.forEach {
            if (it.name != null) {
                item(key = it.id) {
                    Column {
                        CatBreedItem(breed = it, onClick = {
                            onCatBreedItemClick(it.id!!)
                        })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CatBreedItem(breed: BreedItem, onClick: () -> Unit) {
    Card(
        backgroundColor = Color(229, 204, 255),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = breed.name!!,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            breed.image?.url?.let { url ->
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
            breed.description?.let { text ->
                Text(text = text, style = MaterialTheme.typography.h6)
            }
        }
    }
}