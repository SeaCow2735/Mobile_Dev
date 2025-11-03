package com.example.smarttasksapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smarttasksapp.TasksViewModel
import com.example.smarttasksapp.TaskDto
import com.example.smarttasksapp.TasksListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListScreen(
    vm: TasksViewModel,
    onOpenDetail: (Int) -> Unit
) {
    val state by vm.listState.collectAsState()

    LaunchedEffect(Unit) { vm.loadTasks() }

    Scaffold(topBar = { TopAppBar(title = { Text("SmartTasks") }) }) { inner ->
        Box(Modifier.fillMaxSize().padding(inner), contentAlignment = Alignment.Center) {
            when (val s = state) {
                TasksListState.Loading -> CircularProgressIndicator()
                is TasksListState.Error -> Text("Lỗi: ${s.message}", color = Color.Black)
                is TasksListState.Success -> {
                    val items = s.tasks
                    android.util.Log.d("UI", "list items=${items.map { it.id }}")
                    if (items.isEmpty()) {
                        // EmptyView
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Warning, contentDescription = null)
                            Text("No Tasks Yet!", color = Color.Black)
                            Text("Stay productive—add something to do", color = Color.Black)
                        }
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(items, key = { it.id }) { task ->
                                TaskCard(task = task, onClick = { onOpenDetail(task.id) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskCard(task: TaskDto, onClick: () -> Unit) {
    val containerColor = when (task.priority.lowercase()) {
        "high" -> Color(0xFFFFE3E3)
        "medium" -> Color(0xFFFFF4CC)
        else -> Color(0xFFE8F7FF)
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(task.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Text(task.description, style = MaterialTheme.typography.bodyMedium, maxLines = 2, color = Color.Black)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                AssistChip(label = { Text("Status: ${task.status}", color = Color.Black) }, onClick = {})
                AssistChip(label = { Text("Priority: ${task.priority}", color = Color.Black) }, onClick = {})
            }
            Text("Due: ${task.dueDate.take(10)}   ${task.dueDate.drop(11).take(5)}", color = Color.Black)
        }
    }
}
