package com.example.myapplication.profile

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.Home.Banner

@Composable
fun ProfileScreen() {
    val sections = listOf(
        Section("최근 방문", listOf(
            SectionItemData("전체", Icons.Filled.List),
            SectionItemData("뷰티미용", Icons.Filled.Face),
            SectionItemData("가구인테리어", Icons.Filled.AccountBox),
            SectionItemData("생활주방", Icons.Filled.Create)
        )),
        Section("당근소식", listOf(
            SectionItemData("당근 아파트먼트", Icons.Filled.Person),
            SectionItemData("진행중인 이벤트", Icons.Filled.Star),
            SectionItemData("공지사항", Icons.Filled.Info)
        )),
        Section("나의 거래", listOf(
            SectionItemData("관심목록", Icons.Filled.Favorite),
            SectionItemData("판매내역", Icons.Filled.Menu),
            SectionItemData("구매내역", Icons.Filled.ShoppingCart),
            SectionItemData("모아보기", Icons.Filled.DateRange),
            SectionItemData("중고거래 가계부", Icons.Filled.AccountBox)
        )),
        Section("기타", listOf(
            SectionItemData("내 동네 설정", Icons.Filled.Place),
            SectionItemData("동네 인증하기", Icons.Filled.Search),
            SectionItemData("키워드 알림 설정", Icons.Filled.Phone)
        ))
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(Color.White),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                ProfileBanner()
            }
            sections.forEach { section ->
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        SectionHeader(title = section.title)
                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        )
                    }

                }
                items(section.items) { item ->
                    SectionItem(text = item.text, icon = item.icon)
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun SectionItem(text: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
    }
}

data class Section(val title: String, val items: List<SectionItemData>)
data class SectionItemData(val text: String, val icon: ImageVector)