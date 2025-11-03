package com.example.smarttasksapp


import com.example.smarttasksapp.TasksAPI
import com.example.smarttasksapp.TaskDto
import com.example.smarttasksapp.ApiResponse
class TasksRepository(
    private val api: TasksAPI = TasksAPI.create()
) {
    suspend fun getTasks(): Result<List<TaskDto>> = runCatching {
        val res = api.getTasks()
        if (!res.isSuccess) error(res.message.ifBlank { "Request failed" })
        res.data ?: emptyList()
    }

    suspend fun getTaskById(id: Int): Result<TaskDto> = runCatching {
        val res = api.getTaskById(id)
        if (!res.isSuccess) error(res.message.ifBlank { "Request failed" })
        res.data ?: error("Empty data")
    }

    suspend fun deleteTask(id: Int): Result<Unit> = runCatching {
        val res = api.deleteTask(id)
        if (!res.isSuccess) error(res.message.ifBlank { "Request failed" })
        Unit
    }
}
