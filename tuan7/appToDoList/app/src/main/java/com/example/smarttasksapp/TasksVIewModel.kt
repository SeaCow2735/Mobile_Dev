package com.example.smarttasksapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttasksapp.TaskDto
import com.example.smarttasksapp.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//List state
sealed interface TasksListState {
    object Loading : TasksListState
    data class Success(val tasks: List<TaskDto>) : TasksListState
    data class Error(val message: String) : TasksListState
}

//Detail state
sealed interface TaskDetailState {
    object Loading : TaskDetailState
    data class Success(val task: TaskDto) : TaskDetailState
    data class Error(val message: String) : TaskDetailState
}

//One-off event cho xóa
sealed interface DeleteEvent {
    object Idle : DeleteEvent
    object Success : DeleteEvent
    data class Error(val message: String) : DeleteEvent
}

class TasksViewModel(
    private val repo: TasksRepository = TasksRepository()
) : ViewModel() {

    private val _listState = MutableStateFlow<TasksListState>(TasksListState.Loading)
    val listState: StateFlow<TasksListState> = _listState.asStateFlow()

    private val _detailState = MutableStateFlow<TaskDetailState>(TaskDetailState.Loading)
    val detailState: StateFlow<TaskDetailState> = _detailState.asStateFlow()

    private val _deleteEvent = MutableStateFlow<DeleteEvent>(DeleteEvent.Idle)
    val deleteEvent: StateFlow<DeleteEvent> = _deleteEvent.asStateFlow()

    fun loadTasks() {
        _listState.value = TasksListState.Loading
        viewModelScope.launch {
            val result = repo.getTasks()
            _listState.value = result.fold(
                onSuccess = { TasksListState.Success(it) },
                onFailure = { TasksListState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    fun loadTask(id: Int) {
        _detailState.value = TaskDetailState.Loading
        viewModelScope.launch {
            val result = repo.getTaskById(id)
            _detailState.value = result.fold(
                onSuccess = { TaskDetailState.Success(it) },
                onFailure = { TaskDetailState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    private fun removeTaskLocally(taskId: Int) {
        val cur = _listState.value
        if (cur is TasksListState.Success) {
            val before = cur.tasks.size
            val afterList = cur.tasks.filterNot { it.id == taskId }
            android.util.Log.d("VM", "removeTaskLocally id=$taskId size: $before -> ${afterList.size}")
            _listState.value = cur.copy(tasks = afterList)
        } else {
            android.util.Log.d("VM", "removeTaskLocally ignored; listState=$cur")
        }
    }

    fun toggleSubtask(taskId: Int, subtaskId: Int, checked: Boolean) {
        val d = _detailState.value
        if (d is TaskDetailState.Success) {
            val updatedSub = d.task.subtasks.map { s ->
                if (s.id == subtaskId) s.copy(isCompleted = checked) else s
            }
            val updatedTask = d.task.copy(subtasks = updatedSub)
            _detailState.value = TaskDetailState.Success(updatedTask)
            val cur = _listState.value
            if (cur is TasksListState.Success) {
                _listState.value = cur.copy(
                    tasks = cur.tasks.map { if (it.id == taskId) updatedTask else it }
                )
            }
        }
    }

    fun deleteTask(taskId: Int) {
        android.util.Log.d("VM", "deleteTask start id=$taskId")
        _deleteEvent.value = DeleteEvent.Idle
        viewModelScope.launch {
            val result = repo.deleteTask(taskId)
            _deleteEvent.value = result.fold(
                onSuccess = {
                    android.util.Log.d("VM", "deleteTask success id=$taskId -> remove local")
                    removeTaskLocally(taskId)
                    DeleteEvent.Success
                },
                onFailure = { e ->
                    android.util.Log.e("VM", "deleteTask failed: ${e.message}")
                    DeleteEvent.Error(e.message ?: "Unknown error")
                }
            )
        }
    }

    fun clearDeleteEvent() { _deleteEvent.value = DeleteEvent.Idle }
}
