package com.comye1.catcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.comye1.catcat.CatPic.CatPicScreen
import com.comye1.catcat.catfact.CatFactScreen
import com.comye1.catcat.repository.Repository
import com.comye1.catcat.ui.theme.CatCatTheme

class MainActivity : ComponentActivity() {

    private lateinit var catFactViewModel: CatFactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val repository = Repository.get()
            catFactViewModel = ViewModelProvider(
                this,
                CatViewModelFactory(repository)
            ).get(CatFactViewModel::class.java)

            val navController = rememberNavController()

            CatCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    NavHost(navController = navController, startDestination = "catFact") {
                        composable("catFact") {
                            CatFactScreen(viewModel = catFactViewModel)
                        }
                        composable("catPic") {
                            CatPicScreen()
                        }
                    }

                }
            }
        }
    }
}