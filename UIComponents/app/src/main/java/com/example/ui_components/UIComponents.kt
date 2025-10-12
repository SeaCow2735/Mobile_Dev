package com.example.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun UIComponentsScreen(navController: NavController) {
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
                text = "UI Components List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Display",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
            Button(
                onClick = {navController.navigate(Routes.TextDetail)},
                modifier = Modifier
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6fb5f6))

            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Text("Text", color = Color.Black)
                    Text("Displays Text", color = Color.Black)
                }
            }

            Button(
                onClick = {navController.navigate(Routes.ImageDetail)},
                modifier = Modifier
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6fb5f6))
            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Text("Image", color = Color.Black)
                    Text("Displays an image", color = Color.Black)
                }
            }

            Button(
                onClick = {navController.navigate(Routes.IconDetail)},
                modifier = Modifier
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6fb5f6))
            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Text("Icon", color = Color.Black)
                    Text("Displays an Icon", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Input",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
            Button(
                onClick = {navController.navigate(Routes.TextField)},
                modifier = Modifier
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6fb5f6))

            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Text("TextField", color = Color.Black)
                    Text("Input field for text", color = Color.Black)
                }
            }

            Button(
                onClick = {navController.navigate(Routes.PasswordField)},
                modifier = Modifier
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6fb5f6))
            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Text("PasswordField", color = Color.Black)
                    Text("Input field for passwords", color = Color.Black)
                }

            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Layout",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6fb5f6))

            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Text("Column", color = Color.Black)
                    Text("Arranges elements vertically", color = Color.Black)
                }
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6fb5f6))
            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Text("Row", color = Color.Black)
                    Text("Arranges elements horizontally", color = Color.Black)
                }
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6fb5f6))
            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Text("Box", color = Color.Black)
                    Text("Arranges elements in a container like a box", color = Color.Black)
                }
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6fb5f6))
            ) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Text("List", color = Color.Black)
                    Text("Arranges elements like a list", color = Color.Black)
                }
            }
        }
    }
}

