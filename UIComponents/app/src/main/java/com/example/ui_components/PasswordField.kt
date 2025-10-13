package com.example.ui_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PasswordFieldScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    label: String = "Password"
) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                shape = RoundedCornerShape(8.dp)
            ) { Text("← Back", color = Color.White) }

            Spacer(Modifier.width(16.dp))

            Text(
                text = "Password Field",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it

                        // Kiểm tra điều kiện đơn giản
                        errorMessage = when {
                            password.isBlank() -> "Mật khẩu không được để trống"
                            password.contains(" ") -> "Mật khẩu không được chứa khoảng trắng"
                            password.length < 6 -> "Mật khẩu phải có ít nhất 6 ký tự"
                            else -> ""
                        }
                    },
                    label = { Text("Nhập mật khẩu") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible)
                                    Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (passwordVisible)
                                    "Ẩn mật khẩu" else "Hiện mật khẩu"
                            )
                        }
                    },
                    isError = errorMessage.isNotEmpty(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color(0xFF2196F3),
                        focusedIndicatorColor = if (errorMessage.isEmpty()) Color(0xFF2196F3) else Color.Red,
                        unfocusedIndicatorColor = if (errorMessage.isEmpty()) Color(0xFFB0BEC5) else Color.Red,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = if (errorMessage.isEmpty()) Color(0xFF2196F3) else Color.Red,
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                // Thông báo lỗi (nếu có)
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 32.dp, top = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { navController.popBackStack() },
                    enabled = errorMessage.isEmpty() && password.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (errorMessage.isEmpty()) Color(0xFF2196F3)
                        else Color.LightGray
                    )
                ) {
                    Text("Xác nhận", color = Color.White)
                }
            }
        }
    }
}
