package com.example.myapplication.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R

@Composable
fun Banner() {
    Image(
        painter = painterResource(id = R.drawable.home_banner), // drawable 폴더의 배너 이미지
        contentDescription = "Banner Image",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}