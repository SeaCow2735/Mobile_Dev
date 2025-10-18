package pages


import Components.SharedAuthViewModel
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data_flow.R
import com.example.data_flow.Routes
import com.example.data_flow.components.OtpInputField
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext


@Composable
fun NewPasswordScreen(
    navController: NavController,
    sharedViewModel : SharedAuthViewModel
    ) {
    val bg = Color(0xFFEAF5FF)
    val card = Color(0xFFD7EBFF)
    val arrowBg = Color(0xFF111827)
    val titleBlue = Color(0xFF2196F3)
    var otpValue = rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 8.dp, top = 8.dp)
                .size(40.dp)
                .clip(CircleShape)
                .background(card)
                .clickable { navController.popBackStack() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Back",
                tint = titleBlue
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .wrapContentHeight()
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(R.drawable.uth_logo),
                contentDescription = "UTH LOGO",
                modifier = Modifier
                    .height(180.dp)
                    .width(180.dp)
            )

            Text(
                "Smart Tasks",
                color = Color(0xFF2196F3),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "Mật Khẩu Mới",
                color = Color.Black,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            var password2 by rememberSaveable { mutableStateOf("") }

            var passwordError by remember { mutableStateOf<String?>(null) }
            var password2Error by remember { mutableStateOf<String?>(null) }

            var passwordVisible by rememberSaveable { mutableStateOf(false) }

            fun validatePassword(input: String): String? = when {
                input.isBlank() -> "Mật khẩu không được để trống"
                input.contains(" ") -> "Mật khẩu không được chứa khoảng trắng"
                input.length < 6 -> "Mật khẩu phải có ít nhất 6 ký tự"
                else -> null
            }

            fun validateConfirm(pass: String, confirm: String): String? = when {
                confirm.isBlank() -> "Vui lòng nhập lại mật khẩu"
                confirm.contains(" ") -> "Mật khẩu không được chứa khoảng trắng"
                confirm.length < 6 -> "Mật khẩu phải có ít nhất 6 ký tự"
                confirm != pass -> "Mật khẩu nhập lại không khớp"
                else -> null
            }

// Mật khẩu mới
            OutlinedTextField(
                value = sharedViewModel.password,
                onValueChange = {
                    sharedViewModel.password = it
                    passwordError = validatePassword(it)
                    // Đồng thời re-validate ô nhập lại nếu đã gõ
                    if (password2.isNotEmpty()) {
                        password2Error = validateConfirm(sharedViewModel.password, password2)
                    }
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
                supportingText = { passwordError?.let { Text(it, color = Color.Red, fontSize = 12.sp) } },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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

// Nhập lại mật khẩu
            OutlinedTextField(
                value = password2,
                onValueChange = {
                    password2 = it
                    password2Error = validateConfirm(sharedViewModel.password, it)
                },
                label = { Text("Nhập lại mật khẩu") },
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
                isError = password2Error != null,
                supportingText = { password2Error?.let { Text(it, color = Color.Red, fontSize = 12.sp) } },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color(0xFF2196F3),
                    focusedIndicatorColor = if (password2Error == null) Color(0xFF2196F3) else Color.Red,
                    unfocusedIndicatorColor = if (password2Error == null) Color(0xFFB0BEC5) else Color.Red,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = if (password2Error == null) Color(0xFF2196F3) else Color.Red,
                    unfocusedLabelColor = Color.Gray
                )
            )

            val formValid = passwordError == null &&
                    password2Error == null &&
                    sharedViewModel.password.isNotBlank() &&
                    password2.isNotBlank()

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

        }
    }
}