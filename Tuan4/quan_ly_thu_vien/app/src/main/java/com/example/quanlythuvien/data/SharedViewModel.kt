package com.example.quanlythuvien.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedVM : ViewModel() {
    var students by mutableStateOf(listOf<String>())
        private set

    fun addStudent(name: String) {
        students = students + name
    }

    var books by mutableStateOf(listOf<String>())
    fun addBook(book_name: String) {
        books = books + "Sách ${book_name}"
    }
}
