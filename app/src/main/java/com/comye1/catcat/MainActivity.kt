package com.comye1.catcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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

            val scope = rememberCoroutineScope()
//            scope.launch {
//
//            }
            var errorText by remember {
                mutableStateOf("")
            }
            scope.launch {
                try {
                    val result = CatFactApi.retrofitService.getCatFacts()
                    catFact.value = CatFactApi.retrofitService.getCatFacts() as ArrayList<CatFactItem>

                }catch (e: Exception) {
                    errorText = e.message.toString()
                }
            }


            CatCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {

                        CatFactResult(catFact.value)
                        Text(text = errorText)
                    }
                }
            }
        }
    }
}

@Composable
fun CatFactResult(res: ArrayList<CatFactItem>) {
    Column() {
        for(item in res) {
            Text(text = item.toString())
        }
    }

}
