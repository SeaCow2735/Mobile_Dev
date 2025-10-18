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
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun ForgetPasswordScreen(
    navController: NavController,
    sharedViewModel: SharedAuthViewModel
    ){
    val context = LocalContext.current
    val bg = Color(0xFFEAF5FF)
    val card = Color(0xFFD7EBFF)
    val arrowBg = Color(0xFF111827)
    val titleBlue = Color(0xFF2196F3)
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

            Text("Smart Tasks",
                color = Color(0xFF2196F3),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            Text("Quên Mật Khẩu",
                color = Color.Black,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(25.dp))

            var email by rememberSaveable { mutableStateOf("") }
            var emailError by remember { mutableStateOf<String?>(null) }

            fun validateEmail(input: String): String? = when {
                input.isBlank() -> "Email không được để trống"
                !Patterns.EMAIL_ADDRESS.matcher(input).matches() -> "Email không hợp lệ"
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

          val formValid = emailError == null&& email.isNotBlank()

            Button(
                onClick = {
                    if(email.trim()==sharedViewModel.email.trim())
                    {
                        sharedViewModel.passClear()
                        navController.navigate(Routes.Verification)
                    }
                    else
                    {
                        Toast.makeText(context, "Email không trùng với tài khoản đã đăng ký", Toast.LENGTH_SHORT).show()
                    }
                          },
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