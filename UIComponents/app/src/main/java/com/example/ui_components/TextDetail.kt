package com.example.ui_components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun TextDetailScreen(navController: NavController) {
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
                text = "Text Detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top=4.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("The",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(3.dp)
                )
                Text("quick",
                    fontSize = 24.sp,
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF993300),
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                baselineShift = BaselineShift(0.7f)
                            )
                        ) {
                            append("B")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF993300),
                                fontSize = 24.sp,
                                baselineShift = BaselineShift(0.7f)
                            )
                        ) {
                            append("rown")
                        }
                    },
                    modifier = Modifier.alignByBaseline()
                )

            }

            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("fox",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(3.dp)
                )
                Text("jumps",
                    letterSpacing = 5.sp,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(3.dp)
                )
                Text("over",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.width(7.dp))
            }

            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("the",
                    fontSize = 24.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = "lazy",
                    fontFamily = FontFamily(Font(R.font.dancing_script_variablefont_wght)),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(all = 3.dp)
                )


                Text("dog.",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(3.dp)
                )

                Spacer(modifier = Modifier.width(55.dp))
            }
        }
    }
}
