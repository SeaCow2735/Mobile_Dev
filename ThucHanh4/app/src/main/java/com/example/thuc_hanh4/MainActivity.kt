package com.example.thuc_hanh4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thuc_hanh4.ui.theme.Thuc_hanh4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Thuc_hanh4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    AgeCheckingScreen()
                }
            }
        }
    }
}

@Composable
fun AgeCheckingScreen() {
    var ageText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Kiểm tra độ tuổi",
            color = Color.Blue,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = ageText,
            onValueChange = { ageText = it },
            label = { Text("Nhập tuổi") },
            placeholder = { Text("18", color = Color.Gray.copy(alpha = 0.6f)) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFF222222),
                unfocusedTextColor = Color(0xFF222222),

                focusedBorderColor = Color(0xFF3B82F6),     // viền khi focus
                unfocusedBorderColor = Color(0xFF9CA3AF),   // viền khi chưa focus

                cursorColor = Color(0xFF3B82F6),            // màu con trỏ

                focusedLabelColor = Color(0xFF3B82F6),      // label khi focus
                unfocusedLabelColor = Color(0xFF6B7280),    // label khi chưa focus

                focusedLeadingIconColor = Color(0xFF3B82F6),
                unfocusedLeadingIconColor = Color(0xFF6B7280),

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val age = ageText.toIntOrNull()
                result = when {
                    age == null -> "Vui lòng nhập số hợp lệ."
                    age < 2 -> "Em bé"
                    age in 2..59 -> "Người lớn"
                    else -> "Người già"
                }
            },
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text("kiểm tra",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        result?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}



