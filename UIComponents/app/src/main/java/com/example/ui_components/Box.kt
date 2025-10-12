package com.example.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BoxScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("← Back", color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Box",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier
            .background(Color(0xFFC7C7C7), shape = RoundedCornerShape(8.dp))
            .height(100.dp)
            .fillMaxWidth()
            .padding(10.dp),

            ){
        }
        Spacer(Modifier.height(20.dp))

        Box(modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(Color(0xFFD7F689))
            .height(150.dp)
            .fillMaxWidth()

            ){
            Image(painterResource(id = R.drawable.uth_img),
                contentDescription = "uth",
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
            )
        }
        Spacer(Modifier.height(20.dp))

        Box(modifier = Modifier
            .background(Color(0xFFA8EEFE), shape = RoundedCornerShape(8.dp))
            .height(100.dp)
            .fillMaxWidth()
            .border(width = 5.dp, color = Color.Red, shape = RoundedCornerShape(10.dp))
            .padding(10.dp),

            ){
        }
        Spacer(Modifier.height(20.dp))

        Box(modifier = Modifier
            .background(Color(0xFFA8EEFE).copy(alpha = 0.4f), shape = RoundedCornerShape(8.dp))
            .height(100.dp)
            .fillMaxWidth()
            .padding(10.dp),

            ){
        }
        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier.size(200.dp)
        ) {
            Image(painter = painterResource(R.drawable.uth_img), contentDescription = null)
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )
            Text(
                text = "UTH",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(Modifier.height(20.dp))

        Box(modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF42A5F5), Color(0xFF7E57C2))
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .height(100.dp)
            .fillMaxWidth()
            .padding(10.dp),

            ){

        }
        Spacer(Modifier.height(20.dp))
    }
}