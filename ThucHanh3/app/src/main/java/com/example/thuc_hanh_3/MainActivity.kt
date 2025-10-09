package com.example.thuc_hanh_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thuc_hanh_3.ui.theme.Thuc_hanh_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Thuc_hanh_3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    CheckedEmailScreen()
                }
            }
        }
    }
}

@Composable
fun CheckedEmailScreen() {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf<Boolean?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Thực hành 03",
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )


        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                message = ""
                isValid = null
            },
            label = { Text("Email", fontSize = 16.sp) },
            textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
        )


        if (isValid != null) {
            Text(
                text = message,
                color = if (isValid == true) Color(0xFF2ECC71) else Color.Red,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.Start)
            )
        }


        Button(
            onClick = {
                if (isValidEmail(email)) {
                    message = "Email hợp lệ"
                    isValid = true
                } else {
                    message = "Email không đúng định dạng"
                    isValid = false
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099FF)),
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Kiểm tra", color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)
        }
    }
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckedEmail() {
    Thuc_hanh_3Theme {
        CheckedEmailScreen()
    }
}
