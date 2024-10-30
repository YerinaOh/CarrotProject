@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.Detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.BottomBar
import com.example.myapplication.Home.ListItem
import com.example.myapplication.R

@Composable
fun DetailScreen(item: ListItem, onDismiss: () -> Unit) {
    Scaffold(
        bottomBar = {
            BottomBar(item)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    Image(
                        painter = painterResource(id = item.imageUrl),
                        contentDescription = "Item Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.ondo),
                    contentDescription = "Item Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.White)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White)
                ) {
                    Text(text = item.title, style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = item.description, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}