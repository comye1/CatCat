package com.comye1.catcat.catfact

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comye1.catcat.R
import com.comye1.catcat.catfact.database.CatFactItemLocal
import com.comye1.catcat.ui.theme.Purple200
import com.comye1.catcat.utils.CatScreen

@Composable
fun CatFactScreen(viewModel: CatFactViewModel) {

    val catFacts by viewModel.catFacts.observeAsState()

    CatScreen(title = "Cat Facts") {
        Box(Modifier.fillMaxSize()) {
            if (catFacts == null) {
                LoadingAnimation(Modifier.align(Alignment.Center))
            } else {
                CatFactResult(res = catFacts!!)
            }
        }
    }
}

@Composable
fun CatFactResult(res: List<CatFactItemLocal>) {
    LazyColumn { // 스크롤
        res.forEachIndexed { index, catFactItem ->
            item (key = catFactItem.id) {
                CatFactCardItem(index + 1, item = catFactItem)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        item {
            Spacer(modifier = Modifier.height(40.dp)) // bottom bar
        }
    }
}

@Composable
fun CatFactCardItem(number: Int, item: CatFactItemLocal) {
    Card(
        backgroundColor = Color(229, 204, 255),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                text = number.toString(),
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 4.dp))
                    .background(Purple200)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically),
                color = Color.White,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = item.text, style = MaterialTheme.typography.h6)
        }
    }
}

@Composable
fun LoadingAnimation(modifier: Modifier = Modifier) {
    val angle = rememberInfiniteTransition()
    val loadingAnim by angle.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec =
        infiniteRepeatable(
            tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Image(
        painter = painterResource(id = R.drawable.loading),
        contentDescription = "loading",
        modifier = modifier
            .size(64.dp)
            .rotate(loadingAnim)
    )
}