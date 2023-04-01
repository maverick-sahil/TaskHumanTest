package com.example.taskhumanhometest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.taskhumanhometest.R
import com.example.taskhumanhometest.presentation.bottomnavigation.BottomNavItem
import com.example.taskhumanhometest.presentation.ui.theme.TaskHumanHomeTestTheme
import com.example.taskhumanhometest.presentation.ui.theme.tabSelected
import com.example.taskhumanhometest.presentation.ui.theme.tabUnSelected
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskHumanHomeTestTheme {
                // create a NavController object to handle navigation
                val navController = rememberNavController()
                Scaffold(
                    // set bottom bar using the custom BottomNavigationBar composable
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Discover",
                                    route = "discover",
                                    icon = R.drawable.ic_discover
                                ),
                                BottomNavItem(
                                    name = "Reconnect",
                                    route = "reconnect",
                                    icon = R.drawable.ic_connect
                                ),
                                BottomNavItem(
                                    name = "Bookings",
                                    route = "bookings",
                                    icon = R.drawable.ic_bookings
                                ),
                                BottomNavItem(
                                    name = "Messages",
                                    route = "messages",
                                    icon = R.drawable.ic_messages
                                ),
                                BottomNavItem(
                                    name = "Blogs",
                                    route = "blogs",
                                    icon = R.drawable.ic_blogs
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) { paddingValues ->
                    // set main content using a Column and Navigation composable
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = paddingValues.calculateBottomPadding())
                    ) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    // set up the NavHost to handle navigation
    NavHost(navController = navController, startDestination = "discover") {
        composable("discover") {
            // retrieve the MainViewModel instance using Hilt dependency injection
            val mainViewModel = hiltViewModel<MainViewModel>()
            DiscoverScreen(mainViewModel)
        }
        composable("reconnect") {
            ReconnectScreen()
        }
        composable("bookings") {
            BookingsScreen()
        }
        composable("messages") {
            MessagesScreen()
        }
        composable("blogs") {
            BlogsScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    // get the current destination of the NavController
    val backStackEntry = navController.currentBackStackEntryAsState()
    // set up the BottomNavigation composable
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                // set the item as selected if it's the current destination
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = tabSelected,
                unselectedContentColor = tabUnSelected,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.name
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun ReconnectScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "ReconnectScreen")
    }
}

@Composable
fun BookingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "BookingsScreen")
    }
}

@Composable
fun MessagesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "MessagesScreen")
    }
}

@Composable
fun BlogsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "BlogsScreen")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskHumanHomeTestTheme {
        Greeting("Android")
    }
}