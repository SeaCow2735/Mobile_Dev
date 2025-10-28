package com.example.thuchanh1

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SecondScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as Activity
    val auth = remember { FirebaseAuth.getInstance() }
    val user = auth.currentUser

    LaunchedEffect(user) {
        if (user == null) {
            navController.navigate(Routes.Home) {
                popUpTo(Routes.SecondScreen) { inclusive = true }
            }
        }
    }

    if (user == null) return

    val name = user.displayName ?: "Người dùng"
    val email = user.email ?: "(không có email)"
    val photoUrl = user.photoUrl

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(0.92f)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar
                AsyncImage(
                    model = photoUrl,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Nút Đăng xuất (Sign out)
        Button(
            onClick = {
                // 1) Đăng xuất Firebase (hết phiên Firebase)
                auth.signOut()
                // 2) Đăng xuất Google trên thiết bị cho app này
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val client = GoogleSignIn.getClient(activity, gso)
                client.signOut().addOnCompleteListener {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.SecondScreen) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(0.92f),
            colors = ButtonDefaults.buttonColors()
        ) {
            Text("Đăng xuất")
        }

        Spacer(Modifier.height(12.dp))

        // Nút Thu hồi uỷ quyền (Revoke access)
        Button(
            onClick = {
                // 1) Xoá phiên Firebase
                auth.signOut()
                // 2) Thu hồi quyền đã cấp (lần sau đăng nhập sẽ xin lại từ đầu)
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val client = GoogleSignIn.getClient(activity, gso)
                client.revokeAccess().addOnCompleteListener {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.SecondScreen) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(0.92f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Text("Thu hồi uỷ quyền (Revoke)")
        }
    }
}
