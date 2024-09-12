package com.example.myapplication.profile

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopProfileBar() {
    TopAppBar(
        title = { Text("프로필", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
        actions = {
            IconButton(onClick = { /* 설정 버튼 클릭 시 동작 */ }) {
                Icon(Icons.Filled.Settings, contentDescription = "설정")
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}