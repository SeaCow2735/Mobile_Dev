package com.example.thuchanh2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thuchanh2.ui.theme.ThucHanh2Theme
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThucHanh2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F5F5)
                ) {
                    NumberScreen()
                }
            }
        }
    }
}

@Composable
fun NumberScreen() {
    var input by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(160.dp)) // đẩy xuống giữa màn hình một chút

        Text(
            text = "Thực hành 02",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF222222).copy(alpha = 1f)
        )

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Ô nhập
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                singleLine = true,
                placeholder = { Text(
                    text = "Nhập vào số lượng",
                    color = Color(0xFF222222).copy(alpha = 0.5f)
                )},
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF222222),   // màu chữ khi nhập (focus)
                    unfocusedTextColor = Color(0xFF222222), // màu chữ khi nhập (chưa focus)
                    cursorColor = Color(0xFF222222)         // màu con trỏ
                )

            )

            Spacer(Modifier.width(12.dp))

            // Nút "Tạo"




            Button(
                onClick = {
                    val n = input.trim().toIntOrNull()
                    if (n == null) {
                        error = "Dữ liệu bạn nhập không hợp lệ"
                        count = 0
                    } else {
                        error = null
                        count = n.coerceIn(0, 200) // giới hạn tránh tạo quá nhiều
                    }
                },
                modifier = Modifier
                    .width(88.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Tạo")
            }
        }

        // Dòng báo lỗi (khi nhập chữ)
        if (error != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = error!!,
                color = Color(0xFF222222),
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        // Danh sách ô đỏ
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items((1..count).toList()) { number ->
                Button(
                    onClick = { /* nếu cần xử lý khi bấm ô đỏ */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE74C3C), // đỏ
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = number.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
