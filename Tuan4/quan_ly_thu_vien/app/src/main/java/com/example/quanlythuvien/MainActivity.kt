package com.example.quanlythuvien

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") { Homepage(navController) }
            }
        }
    }
}

@Composable
fun Homepage(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hệ thống\nQuản lý Thư viện",
                fontSize = 32.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(30.dp))

            //STATE
            var student by rememberSaveable { mutableStateOf("Nguyen Van A") }
            var books by rememberSaveable { mutableStateOf(listOf("Sách 01", "Sách 02")) }
            var checked by rememberSaveable { mutableStateOf(setOf(0, 1)) }

            //SINH VIÊN
            Text(
                text = "Sinh viên",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = student,
                    onValueChange = { student = it },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text("Tên sinh viên") }
                )

                Spacer(Modifier.width(12.dp))

                Button(
                    onClick = { /* TODO: mở dialog chọn sinh viên */ },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1766C4)),
                    modifier = Modifier.height(44.dp)
                ) {
                    Text("Thay đổi", color = Color.White)
                }
            }

            //DANH SÁCH SÁCH
            Text(
                text = "Danh sách sách",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE6E6E6))
                    .padding(vertical = 12.dp)
            ) {
                Column(Modifier.fillMaxWidth()) {
                    books.forEachIndexed { index, title ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(Color.White)
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val isChecked = checked.contains(index)
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = {
                                    checked = if (it) checked + index else checked - index
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF8C004A)
                                )
                            )

                            OutlinedTextField(
                                value = title,
                                onValueChange = { new ->
                                    books = books.toMutableList().also { it[index] = new }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp),
                                singleLine = true,
                                shape = RoundedCornerShape(24.dp),
                                placeholder = { Text("Tên sách") },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = Color(0xFFBDBDBD),
                                    unfocusedIndicatorColor = Color(0xFFE0E0E0),
                                    cursorColor = Color.Black
                                )
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { books = books + "Sách ${books.size + 1}" },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1766C4))
            ) {
                Text("Thêm", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
