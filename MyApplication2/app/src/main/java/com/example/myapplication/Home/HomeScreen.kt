package com.example.myapplication.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun HomeScreen() {

    data class ListItem(val imageUrl: Int, val title: String, val description: String, val price: String)

    val sampleItems = List(10) { index ->
        ListItem(
            R.drawable.placeholder_image, // 실제 이미지 리소스로 교체 필요
            "제목 ${index + 1}",
            "이것은 아이템 ${index + 1}의 설명입니다. 여기에 추가 설명이 들어갑니다.",
            "${(index + 1) * 10000}원"
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // 배너 추가
        item {
            Banner()
        }
        items(sampleItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = item.imageUrl),
                        contentDescription = "Item Image",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(text = item.title, fontWeight = FontWeight.Bold)
                        Text(text = item.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Text(text = item.price, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}