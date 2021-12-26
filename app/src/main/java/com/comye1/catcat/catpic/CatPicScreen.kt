package com.comye1.catcat.catpic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.comye1.catcat.R
import com.comye1.catcat.catfact.LoadingAnimation

@Composable
fun CatPicScreen(viewModel: CatPicViewModel) {

    val catPic by viewModel.catPic.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Random Cat", style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.height(16.dp))

        Column(Modifier.fillMaxSize()) {
            if (catPic == null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.3f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LoadingAnimation()
                }
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
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { viewModel.refresh() }) {
                    Text(text = "Refresh")
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "refresh"
                    )
                }
            }
        }
    }
}