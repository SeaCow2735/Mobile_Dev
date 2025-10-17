package com.example.data_flow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data_flow.ui.theme.Data_FlowTheme
import android.util.Patterns
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.data_flow.components.OtpInputField
import pages.ForgetPasswordScreen
import pages.LoginScreen
import pages.VerificationScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Data_FlowTheme {
                NavHost(
                    navController= navController,
                    startDestination = "verification"
                ){
                    composable("main"){RegisterScreen(navController)}
                    composable("login"){ LoginScreen(navController) }
                    composable("forget_password"){ ForgetPasswordScreen(navController) }
                    composable("verification"){ VerificationScreen(navController) }
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                 .wrapContentHeight()
                 .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = painterResource(R.drawable.uth_logo),
                contentDescription = "UTH LOGO",
                modifier = Modifier
                    .height(180.dp)
                    .width(180.dp)
            )

            Text("Smart Tasks",
                    color = Color(0xFF2196F3),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

            Spacer(Modifier.height(20.dp))

            Text("Đăng Ký",
                color = Color.Black,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
                )

            Spacer(Modifier.height(25.dp))

            var email by rememberSaveable { mutableStateOf("") }
            var emailError by remember { mutableStateOf<String?>(null) }

            var password by rememberSaveable { mutableStateOf("") }
            var passwordError by remember { mutableStateOf<String?>(null) }
            var passwordVisible by rememberSaveable { mutableStateOf(false) }

            fun validateEmail(input: String): String? = when {
                input.isBlank() -> "Email không được để trống"
                !Patterns.EMAIL_ADDRESS.matcher(input).matches() -> "Email không hợp lệ"
                else -> null
            }

            fun validatePassword(input: String): String? = when {
                input.isBlank() -> "Mật khẩu không được để trống"
                input.contains(" ") -> "Mật khẩu không được chứa khoảng trắng"
                input.length < 6 -> "Mật khẩu phải có ít nhất 6 ký tự"
                else -> null
            }

            // EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = validateEmail(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                label = { Text("Email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError != null,
                supportingText = {
                    emailError?.let { Text(it, color = Color.Red, fontSize = 12.sp) }
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color(0xFF2196F3),
                    focusedIndicatorColor = if (emailError == null) Color(0xFF2196F3) else Color.Red,
                    unfocusedIndicatorColor = if (emailError == null) Color(0xFFB0BEC5) else Color.Red,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = if (emailError == null) Color(0xFF2196F3) else Color.Red,
                    unfocusedLabelColor = Color.Gray
                )
            )

            Spacer(Modifier.height(16.dp))

            // PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = validatePassword(it)
                },
                label = { Text("Nhập mật khẩu") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisible) "Ẩn mật khẩu" else "Hiện mật khẩu"
                        )
                    }
                },
                isError = passwordError != null,
                supportingText = {
                    passwordError?.let { Text(it, color = Color.Red, fontSize = 12.sp) }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color(0xFF2196F3),
                    focusedIndicatorColor = if (passwordError == null) Color(0xFF2196F3) else Color.Red,
                    unfocusedIndicatorColor = if (passwordError == null) Color(0xFFB0BEC5) else Color.Red,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = if (passwordError == null) Color(0xFF2196F3) else Color.Red,
                    unfocusedLabelColor = Color.Gray
                )
            )

            Spacer(Modifier.height(20.dp))

            val formValid = emailError == null && passwordError == null && email.isNotBlank() && password.isNotBlank()

            Button(
                onClick = {navController.navigate(Routes.Login)},
                enabled = formValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3),
                    disabledContainerColor = Color(0xFFBBDEFB),
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                )
            ) {
                Text("Xác nhận", color = Color.White)
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {navController.navigate(Routes.Login)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3),
                    disabledContainerColor = Color(0xFFBBDEFB),
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                )
            ) {
                Text("Chuyển trang", color = Color.White)
            }
        }
    }

}
