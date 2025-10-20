package com.example.quanlythuvien

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.quanlythuvien.data.SharedVM
import com.example.quanlythuvien.pages.BooksPage
import com.example.quanlythuvien.pages.HomePage
import com.example.quanlythuvien.pages.StudentsPage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main_graph") {
            navigation(startDestination = "home", route = "main_graph" )    {
                composable("home") { backStackEntry ->

                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("main_graph")
                    }

                    val vm: SharedVM = viewModel(parentEntry)

                    HomePage(navController, vm)
                }

                composable("books"){backStackEntry ->

                    val  parentEntry = remember (backStackEntry){
                        navController.getBackStackEntry("main_graph")
                    }

                    val vm: SharedVM=viewModel(parentEntry)

                    BooksPage(navController,vm)
                }

                composable("students"){backStackEntry ->

                    val parentEntry=remember(backStackEntry){
                        navController.getBackStackEntry("main_graph")
                    }

                    val vm: SharedVM = viewModel(parentEntry)

                    StudentsPage(navController, vm)
                }
            }
            }
        }
    }
}