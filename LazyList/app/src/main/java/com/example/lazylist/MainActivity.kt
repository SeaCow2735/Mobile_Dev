package com.example.lazylist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lazylist.ui.theme.LazyListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyListTheme {
                LazyDemoScreen()
            }
        }
    }
}

@Composable
fun LazyDemoScreen() {
    val context = LocalContext.current

    val columnList = remember {
        mutableStateListOf("Android", "Kotlin", "Compose", "Jetpack", "UI Toolkit","IDK","123")
    }
    val rowList = remember {
        mutableStateListOf("1", "2", "3", "4", "5","6","7","8","9","10","11","12","13")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                columnList.add("Item ${columnList.size + 1}")
                rowList.add("${columnList.size + 1}")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
        ) {
            // LazyRow
            Text("LazyRow Demo:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            LazyRow {
                items(rowList) { index ->
                    Text(
                        text = "$index",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .clickable {
                                Toast.makeText(context, "Chọn: $index", Toast.LENGTH_SHORT).show()
                            }
                            .background(Color(0xFF363636)),
                        color = Color.White
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // LazyColumn
            Text("LazyColumn Demo:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(columnList) { index ->
                    Text(
                        text = "$index",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .clickable {
                                Toast.makeText(context, "Chọn: $index", Toast.LENGTH_SHORT).show()
                            }
                            .background(Color(0xFF363636)),
                        color = Color.White
                    )
                }
            }
        }
    }
}
