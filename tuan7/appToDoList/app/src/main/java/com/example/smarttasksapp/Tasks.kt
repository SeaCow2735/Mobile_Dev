package com.example.smarttasksapp

import kotlinx.serialization.Serializable
import java.sql.Date
import java.sql.Time
import java.time.Instant


@Serializable
data class TaskDto(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val category: String,
    val dueDate: String,
    val createdAt: String,
    val updatedAt: String,
    val subtasks: List<SubtaskDto>,
    val attachments: List<AttachmentDto>,
    val reminders: List<ReminderDto>
)

data class SubtaskDto(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

data class AttachmentDto(
    val id: Int,
    val fileName: String,
    val fileUrl: String
)

data class ReminderDto(
    val id: Int,
    val time: String,
    val type: String
)

data class ApiResponse<T>(
    val isSuccess: Boolean,
    val message: String,
    val data: T?
)
