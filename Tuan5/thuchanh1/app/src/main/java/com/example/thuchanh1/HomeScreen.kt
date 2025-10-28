package com.example.thuchanh1

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as Activity

    var userEmail by remember { mutableStateOf<String?>(null) }
    var errorMsg by remember { mutableStateOf<String?>(null) }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken

            if (idToken != null) {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnSuccessListener {
                        val user = FirebaseAuth.getInstance().currentUser
                        userEmail = user?.email
                    }
                    .addOnFailureListener { e ->
                        errorMsg = e.message
                    }
            } else {
                errorMsg = "Không lấy được ID token!"
            }
        } catch (e: ApiException) {
            errorMsg = "Đăng nhập thất bại: ${e.statusCode}"
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "Login with email",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(20.dp))


            OutlinedButton(
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(activity.getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()

                    val client = GoogleSignIn.getClient(activity, gso)
                    launcher.launch(client.signInIntent)

                },
                modifier = Modifier
                    .width(300.dp)
                    .height(65.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFCFCFC),
                    disabledContainerColor = Color(0xFFBBDEFB),
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                )
            ) {
                Image(
                    painter = painterResource(R.drawable.google_logo),
                    contentDescription = null
                )
                Spacer(Modifier.width(25.dp))
                Text("Đăng nhập bằng Google", color = Color.Black)
            }

            Spacer(Modifier.height(25.dp))


            when {
                userEmail != null -> Text("Xin chào: $userEmail", color = Color(0xFF2E7D32))

                errorMsg != null -> Text("Lỗi: $errorMsg", color = Color.Red)
            }

            LaunchedEffect(userEmail) {
                if (userEmail != null) {
                   navController.navigate(Routes.SecondScreen) {
                    popUpTo(Routes.Home) { inclusive = true
                          }
                        }
                    }
                }
            }
        }
    }

