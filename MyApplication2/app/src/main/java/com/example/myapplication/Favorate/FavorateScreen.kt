package com.example.myapplication.Favorate

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R


@Composable
fun FavoriteScreen() {
    data class ListItem(val imageUrl: Int, val title: String, val place: String, val date: String, val conversation: String)

    val sampleItems = List(10) { index ->
        ListItem(
            R.drawable.placeholder_favorate, // 실제 이미지 리소스로 교체 필요
            "유저 ${index + 1}",
            "마곡동",
            "${(index + 1)}일 전",
            conversation = "거래를 너무너무 하고싶어요!"
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
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
                        .height(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = item.imageUrl),
                        contentDescription = "Item Image",
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.CenterVertically)
                            .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {

                        Row(
                            modifier = Modifier
                                .padding(3.dp)
                        ) {
                            Text(text = item.title, fontWeight = FontWeight.Bold)
                            Text(text = " ・ ", fontWeight = FontWeight.Bold)
                            Text(text = item.place, fontWeight = FontWeight.Light)
                            Text(text = " ・ ", fontWeight = FontWeight.Bold)
                            Text(text = item.date, fontWeight = FontWeight.Light)
                        }
                        Text(text = item.conversation, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}