package com.example.smarttasksapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    vm: TasksViewModel,
    onBack: () -> Unit,
    onDeleted: () -> Unit
) {
    val state by vm.detailState.collectAsState()
    val deleteEvent by vm.deleteEvent.collectAsState()

    LaunchedEffect(taskId) { vm.loadTask(taskId) }

    LaunchedEffect(deleteEvent) {
        android.util.Log.d("UI", "deleteEvent=$deleteEvent")
        if (deleteEvent is DeleteEvent.Success) {
            vm.clearDeleteEvent()
            onDeleted()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { vm.deleteTask(taskId) }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { inner ->
        Box(Modifier.fillMaxSize().padding(inner), contentAlignment = Alignment.TopStart) {
            when (val s = state) {
                TaskDetailState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                is TaskDetailState.Error -> Text("Lỗi: ${s.message}", modifier = Modifier.padding(16.dp))
                is TaskDetailState.Success -> {
                    val t = s.task
                    Column(
                        Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(t.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(t.description)

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            SuggestionChip(onClick = {}, label = { Text(t.category) })
                            SuggestionChip(onClick = {}, label = { Text("Status: ${t.status}") })
                            SuggestionChip(onClick = {}, label = { Text("Priority: ${t.priority}") })
                        }

                        Text("Subtasks", style = MaterialTheme.typography.titleMedium)
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            t.subtasks.forEach { sItem ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Checkbox(
                                        checked = sItem.isCompleted,
                                        onCheckedChange = { checked ->
                                            vm.toggleSubtask(taskId = t.id, subtaskId = sItem.id, checked = checked)
                                        }
                                    )
                                    Text(sItem.title)
                                }
                            }
                        }

                        Text("Attachments", style = MaterialTheme.typography.titleMedium)
                        t.attachments.forEach { a ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Email, contentDescription = null)
                                Text(a.fileName, modifier = Modifier.padding(start = 6.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
