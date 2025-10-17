package com.example.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.thuchanh1.Routes

@Composable
fun LazyColumnScreen(
    navController: NavController
) {
    val bg = Color(0xFFEAF5FF)
    val card = Color(0xFFD7EBFF)
    val arrowBg = Color(0xFF111827)
    val titleBlue = Color(0xFF2196F3)

    val labels = (1..10000).map { "%02d".format(it) }
    val items = labels.map { label ->
        "$label | The only way to do great work is to love what you do."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút back tròn
            Box(
                modifier = Modifier
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

            Text(
                text = "LazyColumn",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = titleBlue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(8.dp))

        // List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(items) { line ->
                Surface(
                    color = card,
                    shape = RoundedCornerShape(12.dp),
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Nội dung bên trái (2 dòng)
                        Text(
                            text = line.replace(
                                "The only way to do great work is to love what you do.",
                                "The only way to do great work\nis to love what you do."
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 12.dp),
                            lineHeight = 18.sp
                        )

                        // Nút mũi tên tròn bên phải
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(arrowBg)
                                .clickable {
                                    // Điều hướng khi bấm vào mũi tên
                                    navController.navigate(Routes.Trang3)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowForward,
                                contentDescription = "Go",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LazyColumnScreenPreview() {
    MaterialTheme {
        LazyColumnScreen(rememberNavController())
    }
}
