package com.example.quanlythuvien.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class SharedVM : ViewModel() {

    // --- Danh sách sinh viên + người đang chọn ---
    var students by mutableStateOf(listOf("Nguyen Van A", "Nguyen Thi B", "Nguyen Van C"))
        private set

    var selectedStudent by mutableStateOf(students.first())
        private set

    fun selectStudent(name: String) {
        val n = name.trim()
        if (n.isNotEmpty()) {
            if (!students.contains(n)) students = students + n
            selectedStudent = n
            if (!booksByStudent.containsKey(n)) {
                booksByStudent = booksByStudent.toMutableMap().apply { put(n, emptyList()) }.toMap()
            }
        }
    }

    fun addStudent(name: String) {
        val n = name.trim()
        if (n.isNotEmpty() && !students.contains(n)) {
            students = students + n
            booksByStudent = booksByStudent.toMutableMap().apply { put(n, emptyList()) }.toMap()
        }
    }

    fun removeStudentAt(index: Int) {
        if (index in students.indices) {
            val removed = students[index]
            students = students.toMutableList().also { it.removeAt(index) }
            booksByStudent = booksByStudent.toMutableMap().also { it.remove(removed) }.toMap()
            if (selectedStudent == removed) {
                selectedStudent = students.firstOrNull() ?: ""
            }
        }
    }

    //Sách theo sinh viên
    var booksByStudent by mutableStateOf<Map<String, List<String>>>(
        mapOf(
            "Nguyen Van A" to listOf("Sách 01", "Sách 02"),
            "Nguyen Thi B" to listOf("Sách 01"),
            "Nguyen Van C" to emptyList()
        )
    )
        private set

    fun booksForSelected(): List<String> = booksByStudent[selectedStudent] ?: emptyList()

    fun addBookForSelected(title: String) {
        val n = title.trim()
        if (n.isEmpty() || selectedStudent.isEmpty()) return
        val cur = booksByStudent[selectedStudent] ?: emptyList()
        booksByStudent = booksByStudent.toMutableMap().apply {
            put(selectedStudent, cur + n)
        }.toMap()
    }

    fun updateBookForSelected(index: Int, title: String) {
        val list = booksByStudent[selectedStudent] ?: return
        val n = title.trim()
        if (index !in list.indices || n.isEmpty()) return
        val newList = list.toMutableList().also { it[index] = n }.toList()
        booksByStudent = booksByStudent.toMutableMap().apply {
            put(selectedStudent, newList)
        }.toMap()
    }

    fun removeBookAtForSelected(index: Int) {
        val list = booksByStudent[selectedStudent] ?: return
        if (index !in list.indices) return
        val newList = list.toMutableList().also { it.removeAt(index) }.toList()
        booksByStudent = booksByStudent.toMutableMap().apply {
            put(selectedStudent, newList)
        }.toMap()
    }

    fun clearBooksForSelected() {
        if (selectedStudent.isEmpty()) return
        booksByStudent = booksByStudent.toMutableMap().apply {
            put(selectedStudent, emptyList())
        }.toMap()
    }

    //RANDOM: đổi số lượng sách ngẫu nhiên cho SV đang chọn
    fun randomizeBooksForSelected(min: Int = 0, max: Int = 6) {
        if (selectedStudent.isEmpty()) return
        val pool = listOf(
            "Sách 01","Sách 02","Sách 03","Sách 04","Sách 05",
            "Sách 06","Sách 07","Sách 08","Sách 09","Sách 10"
        )
        val n = Random.nextInt(min, max + 1)
        val newList = pool.shuffled().take(n)
        booksByStudent = booksByStudent.toMutableMap().apply {
            put(selectedStudent, newList)
        }.toMap()
    }
}
