@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Favorate.FavoriteScreen
import com.example.myapplication.Favorate.TopFavorateBar
import com.example.myapplication.profile.TopProfileBar
import com.example.myapplication.Home.HomeScreen
import com.example.myapplication.Home.TopHomeBar
import com.example.myapplication.profile.ProfileScreen

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
                    Screen.Favorite -> TopFavorateBar()
                    Screen.Profile -> TopProfileBar()
                }
            },
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
                composable(Screen.Home.route) { HomeScreen() }
                composable(Screen.Favorite.route) { FavoriteScreen() }
                composable(Screen.Profile.route) { ProfileScreen() }
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