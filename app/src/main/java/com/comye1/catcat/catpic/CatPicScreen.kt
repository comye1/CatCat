package com.comye1.catcat.catpic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.comye1.catcat.catfact.LoadingAnimation
import com.comye1.catcat.utils.CatScreen

@Composable
fun CatPicScreen(viewModel: CatPicViewModel) {

    val catPic by viewModel.catPic.observeAsState()

    viewModel.refresh() // 이렇게 하니 처음에 로딩 애니메이션이 실행된다.

    CatScreen(title = "Random Cat") {
        Column(Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(.8f),
                horizontalArrangement = Arrangement.Center
            ) {
                if (catPic == null) {
                    LoadingAnimation(modifier = Modifier.align(CenterVertically))
                } else {
                    // 이미지
                    Image(
                        painter = rememberImagePainter(
                            data = catPic!!.url,
                            builder = {
//                            transformations(CircleCropTransformation())
                                crossfade(true)
//                            placeholder(R.drawable.loading)
                            },
                        ),
                        contentDescription = "cat image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterVertically)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row( // 이거 왜 밑에 있으면 늦게 뜨는지.. -> 이미지 사이즈를 알지 못해서 다 가려버린 것이었다. 해결!
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { viewModel.refresh() }) {
                    Text(text = "Refresh")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "refresh"
                    )
                }
            }
        }
    }
}