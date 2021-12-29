package com.comye1.catcat.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Photo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class Destination(
    val route: String,
    val name: String,
    val icon: ImageVector
){
    object CatFact : Destination("catFact", "Facts", Icons.Default.Article)
    object CatPic : Destination("catPic", "Pics", Icons.Default.Photo)
    object CatBreed : Destination("catBreed", "Breeds", Icons.Default.Category)
}

@Composable
fun BottomNavigationBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    Column {
        Divider()
        BottomNavigation(
            backgroundColor = Color.White,
            elevation = 0.dp
        ) {
            listOf(Destination.CatFact, Destination.CatPic, Destination.CatBreed).forEach { item ->
                BottomNavigationItem(
                    selected = item.route == currentRoute,
                    enabled = item.route != currentRoute,
                    onClick = { navController.navigate(item.route) },
                    icon = { Icon(imageVector = item.icon, contentDescription = item.name) },
                    label = { Text(item.name) },
                    selectedContentColor = MaterialTheme.colors.primaryVariant,
                    unselectedContentColor = Color.DarkGray,
                    alwaysShowLabel = false
                )
            }
        }
    }

}