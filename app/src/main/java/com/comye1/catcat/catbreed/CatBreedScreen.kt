package com.comye1.catcat.catbreed

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.comye1.catcat.catbreed.models.BreedItem

@Composable
fun CatBreedScreen(viewModel: CatBreedViewModel) {

    val catBreedList = viewModel.catBreeds

    val (query, setQuery) = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Cat Breeds", style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            CatBreedSearchBar(query, setQuery)
        }
        Spacer(modifier = Modifier.height(8.dp))
        CatBreedList(list = catBreedList.filter {
            it.name!!.lowercase().contains(query.lowercase())
        })

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
fun CatBreedList(list: List<BreedItem>) {
    LazyColumn {
        list.forEach {
            if (it.name != null) {
                item {
                    CatBreedItem(breed = it)
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

            }
        }
    }
}

@Composable
fun CatBreedItem(breed: BreedItem) {
    Card(
        backgroundColor = Color(229, 204, 255),
        modifier = Modifier
            .fillMaxWidth()
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