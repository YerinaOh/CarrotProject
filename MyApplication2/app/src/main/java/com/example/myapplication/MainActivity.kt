@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.myapplication

import android.app.LauncherActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.Favorate.FavoriteScreen
import com.example.myapplication.Favorate.TopFavorateBar
import com.example.myapplication.profile.TopProfileBar
import com.example.myapplication.Home.HomeScreen
import com.example.myapplication.Home.TopHomeBar
import com.example.myapplication.profile.ProfileScreen
import com.google.gson.Gson
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.Home.ListItem
import java.net.URLDecoder

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
        var selectedItem by remember { mutableStateOf<ListItem?>(null) }

        if (selectedItem == null) {
        Scaffold(
            topBar = {
                when (currentScreen) {
                    Screen.Home -> TopHomeBar()
                    Screen.Favorite -> TopFavorateBar()
                    Screen.Profile -> TopProfileBar()
                    else -> {}
                }
            },
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
                composable(Screen.Home.route) {
                    HomeScreen { item ->
                        selectedItem = item
                    }
                }
                composable(Screen.Favorite.route) { FavoriteScreen() }
                composable(Screen.Profile.route) { ProfileScreen() }
            }
            // DetailView as an overlay
            selectedItem?.let { item ->
                DetailScreen(item) {
                    selectedItem = null
                }
            }
        }
        } else {
            DetailScreen(selectedItem!!) {
                selectedItem = null
            }
        }
    }
}

@Composable
fun DetailScreen(item: ListItem, onDismiss: () -> Unit) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        bottomBar = {
            BottomBar(item)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = item.imageUrl),
                contentDescription = "Item Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = item.title, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.description, style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Composable
fun BottomBar(item: ListItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_heart), // 실제 프로필 이미지로 교체 필요
                contentDescription = "icon heart",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "가격 제안 불가",
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray
                )
            }
            Button(
                onClick = { /* 채팅 기능 구현 */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA500)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("채팅하기", color = Color.White)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        modifier = Modifier.height(80.dp),
        backgroundColor = Color.White,
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        SimpleBottomTabApp()
    }
}