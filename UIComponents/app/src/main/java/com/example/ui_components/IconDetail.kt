package com.example.ui_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.sharp.*
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun IconDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Header
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
                text = "Icon Detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { IconGroup("Filled Icons", filledIcons()) }
            item { IconGroup("Outlined Icons", outlinedIcons()) }
            item { IconGroup("Rounded Icons", roundedIcons()) }
            item { IconGroup("TwoTone Icons", twoToneIcons()) }
            item { IconGroup("Sharp Icons", sharpIcons()) }
        }
    }
}


@Composable
fun IconGroup(title: String, icons: List<Pair<ImageVector, String>>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            icons.forEach { (icon, label) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = Color(0xFF333333),
                        modifier = Modifier.size(36.dp)
                    )
                    Text(label, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }
}


fun filledIcons() = listOf(
    Icons.Filled.Home to "Home",
    Icons.Filled.Favorite to "Fav",
    Icons.Filled.Settings to "Settings",
    Icons.Filled.Notifications to "Bell",
    Icons.Filled.Person to "User"
)

fun outlinedIcons() = listOf(
    Icons.Outlined.Home to "Home",
    Icons.Outlined.Search to "Search",
    Icons.Outlined.Email to "Mail",
    Icons.Outlined.Star to "Star",
    Icons.Outlined.Delete to "Delete"
)

fun roundedIcons() = listOf(
    Icons.Rounded.Favorite to "Heart",
    Icons.Rounded.Share to "Share",
    Icons.Rounded.LocationOn to "Location",
    Icons.Rounded.Build to "Tools",
    Icons.Rounded.Camera to "Camera"
)

fun twoToneIcons() = listOf(
    Icons.TwoTone.Home to "Home",
    Icons.TwoTone.Person to "User",
    Icons.TwoTone.Favorite to "Fav",
    Icons.TwoTone.Call to "Call",
    Icons.TwoTone.Settings to "Settings"
)

fun sharpIcons() = listOf(
    Icons.Sharp.Home to "Home",
    Icons.Sharp.Email to "Mail",
    Icons.Sharp.Phone to "Phone",
    Icons.Sharp.Warning to "Alert",
    Icons.Sharp.Build to "Tools"
)
