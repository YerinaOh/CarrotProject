package com.example.myapplication.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun ProfileBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 프로필 이미지
                Image(
                    painter = painterResource(id = R.drawable.profile_user), // 실제 프로필 이미지로 교체 필요
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                // 프로필명
                Text(
                    text = "당근고수리나",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
        // 남은 공간을 채우는 Spacer
                Spacer(modifier = Modifier.weight(1f))
                // 프로필 보기 버튼
                Button(
                    onClick = { /* 프로필 보기 동작 */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.height(30.dp)
                ) {
                    Text(
                        "프로필 보기",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                }
            }
            // 페이 이미지
            Image(
                painter = painterResource(id = R.drawable.profile_pay), // 실제 배너 이미지로 교체 필요
                contentDescription = "Banner Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}