package com.dividendcalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dividendcalendar.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDest = navBackStackEntry?.destination

                val bottomItems = listOf(
                    BottomNavItem("Calendar", Screen.Calendar.route, Icons.Default.CalendarMonth),
                    BottomNavItem("Stocks", Screen.StockList.route, Icons.Default.ShowChart),
                )

                Scaffold(
                    bottomBar = {
                        // Hide bottom bar on detail screen
                        if (currentDest?.route != Screen.StockDetail.route) {
                            NavigationBar {
                                bottomItems.forEach { item ->
                                    NavigationBarItem(
                                        icon = { Icon(item.icon, contentDescription = item.label) },
                                        label = { Text(item.label) },
                                        selected = currentDest?.hierarchy?.any { it.route == item.route } == true,
                                        onClick = {
                                            navController.navigate(item.route) {
                                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Calendar.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Calendar.route) {
                            CalendarScreen(onStockClick = { symbol ->
                                navController.navigate(Screen.StockDetail.createRoute(symbol))
                            })
                        }
                        composable(Screen.StockList.route) {
                            StockListScreen(onStockClick = { symbol ->
                                navController.navigate(Screen.StockDetail.createRoute(symbol))
                            })
                        }
                        composable(Screen.StockDetail.route) { backStackEntry ->
                            val symbol = backStackEntry.arguments?.getString("symbol") ?: return@composable
                            StockDetailScreen(symbol = symbol, onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}

private data class BottomNavItem(val label: String, val route: String, val icon: ImageVector)
