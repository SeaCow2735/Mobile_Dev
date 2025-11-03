package com.example.smarttasksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.smarttasksapp.ui.theme.SmartTasksAppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTasksAppTheme {
                val nav = rememberNavController()

                NavHost(navController = nav, startDestination = "home") {
                    navigation(startDestination = "list", route = "home") {

                        composable("list") { listEntry ->
                            val parentEntry = remember(listEntry) { nav.getBackStackEntry("home") }
                            val vm: TasksViewModel = viewModel(parentEntry)

                            TasksListScreen(
                                vm = vm,
                                onOpenDetail = { id -> nav.navigate("detail/$id") }
                            )
                        }

                        composable(
                            route = "detail/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { detailEntry ->
                            val parentEntry = remember(detailEntry) { nav.getBackStackEntry("home") }
                            val vm: TasksViewModel = viewModel(parentEntry)
                            val id = detailEntry.arguments!!.getInt("id")

                            TaskDetailScreen(
                                taskId = id,
                                vm = vm,
                                onBack = { nav.popBackStack() },
                                onDeleted = { nav.popBackStack() }
                            )
                        }
                    }
                }

            }
        }


    }
}
