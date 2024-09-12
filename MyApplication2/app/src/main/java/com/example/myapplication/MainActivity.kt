@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class Screen(val route: String, val icon: ImageVector, val label: String) {
    Home("홈", Icons.Filled.Home, "홈"),
    Favorite("내 근처", Icons.Filled.Favorite, "내 근처"),
    Profile("나의 당근", Icons.Filled.Settings, "나의 당근")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleBottomTabApp()
        }
    }
}

@Composable
fun SimpleBottomTabApp() {
    MyApplicationTheme {
        val navController = rememberNavController()
        val currentScreen by navController.currentScreenAsState()

        Scaffold(
            topBar = {
                when (currentScreen) {
                    Screen.Home -> TopHomeBar()
                    Screen.Favorite -> TopHomeBar()
                    Screen.Profile -> TopHomeBar()
                }
            },
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
                composable(Screen.Home.route) { HomeScreen() }
                composable(Screen.Favorite.route) { FavoriteScreen() }
                composable(Screen.Profile.route) { SettingsScreen() }
            }
        }
    }
}

@Composable
fun TopHomeBar() {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp,
        modifier = Modifier.height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "여의도동",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = "Location Dropdown",
                    tint = Color.Black
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = Color.White,
        contentColor = Color.Black) {
        val currentScreen by navController.currentScreenAsState()

        Screen.values().forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentScreen == screen,
                onClick = { navController.navigate(screen.route) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Composable
fun NavHostController.currentScreenAsState(): State<Screen> {
    val navBackStackEntry by currentBackStackEntryAsState()
    return remember(navBackStackEntry) {
        derivedStateOf {
            Screen.values().find { it.route == navBackStackEntry?.destination?.route } ?: Screen.Home
        }
    }
}

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
                        Text(text = item.description, maxLines = 2, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)
                        Text(text = item.price, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

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

@Composable
fun getIcon(screen: String) = when(screen) {
    "홈" -> Icons.Filled.Home
    "내 근처" -> Icons.Filled.Favorite
    "나의 당근" -> Icons.Filled.Settings
    else -> Icons.Filled.Home
}

@Composable
fun FavoriteScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Favorite Screen")
        Text("여기에 즐겨찾기 항목들을 표시합니다.")
    }
}

@Composable
fun SettingsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Profile Screen")
        Text("여기에 사용자 프로필 정보를 표시합니다.")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        SimpleBottomTabApp()
    }
}