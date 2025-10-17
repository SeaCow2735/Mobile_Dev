package com.example.thuchanh1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
import com.example.ui_components.LazyColumnScreen
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink

@Composable
fun Trang3Screen(navController: NavController)
{
    val bg = Color(0xFFEAF5FF)
    val card = Color(0xFFD7EBFF)
    val arrowBg = Color(0xFF111827)
    val titleBlue = Color(0xFF2196F3)
    Column ( modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
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
                text = "Detail",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = titleBlue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }

        Column (
            modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text("The only way to do great work \n is to love what you do",
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .height(550.dp)
                    .width(300.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFAAC2FE),   // xanh nhạt ở trên
                                Color(0xFF0F7FF2)    // xanh đậm ở dưới
                            )
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "\"The only \nway to do\ngreat work\nis to love\nwhat you\ndo.\"",
                        fontSize = 40.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        lineHeight = 60.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Steve Jobs",
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = 1f),
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(80.dp))
                    Text(
                        buildAnnotatedString {
                            withLink(
                                LinkAnnotation.Url(
                                    "http://quotes.thisgrandpalogs.com/"
                                )

                            ){
                                append("http://quotes.thisgrandpalogs.com/")
                            }
                        },
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                        color = Color.White,
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { navController.navigate(Routes.Main) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .imePadding(),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text("BACK TO ROOT",
                        color = Color.White,
                        fontSize = 24.sp
                    )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Trang3Screen() {
    MaterialTheme {
        Trang3Screen(rememberNavController())
    }
}
