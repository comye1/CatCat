package com.comye1.catcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.comye1.catcat.models.CatFactItem
import com.comye1.catcat.network.CatFactApi
import com.comye1.catcat.ui.theme.CatCatTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var catFact = remember {
                mutableStateOf(arrayListOf<CatFactItem>())
            }

            var loadingState = remember {
                mutableStateOf(false)
            }
            val scope = rememberCoroutineScope()

            var errorText by remember {
                mutableStateOf("")
            }
            scope.launch {
                loadingState.value = true
                try {
                    val result = CatFactApi.retrofitService.getCatFacts() as ArrayList<CatFactItem>
                    catFact.value = result
                    loadingState.value = false

                } catch (e: Exception) {
                    errorText = e.message.toString()
                    loadingState.value = false
                }
            }


            CatCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (loadingState.value) {
                            Text(
                                text = "Loading...",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Column(Modifier.fillMaxSize()){

                            CatFactResult(catFact.value)
                            Text(text = errorText)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CatFactResult(res: ArrayList<CatFactItem>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Today's Cat Fact", style = MaterialTheme.typography.h3)
        res.forEachIndexed { index, catFactItem ->
            CatFactCardItem(index + 1, item = catFactItem)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CatFactCardItem(number: Int, item: CatFactItem) {
    Card(
        backgroundColor = Color(229, 204, 255),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = number.toString())
            Text(text = item.text, style = MaterialTheme.typography.h6)
        }
    }
}
