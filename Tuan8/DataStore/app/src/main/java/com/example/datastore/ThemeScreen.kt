package com.example.datastore

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ThemeScreen(
    selected: ThemeOption,
    onApply: (ThemeOption) -> Unit
) {
    var temp by remember(selected) { mutableStateOf(selected) }


    val previewBg = when (temp) {
        ThemeOption.SYSTEM -> MaterialTheme.colorScheme.background
        ThemeOption.LIGHT  -> Color(0xFFFFFFFF)
        ThemeOption.DARK   -> Color(0xFF121212)
        ThemeOption.PINK   -> Color(0xFFF8BBD0)
        ThemeOption.SKY    -> Color(0xFFE3F2FD)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = previewBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Setting", style = MaterialTheme.typography.headlineSmall)

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ThemeChip(Color(0xFF121212), "Dark",  temp == ThemeOption.DARK)  { temp = ThemeOption.DARK }
                ThemeChip(Color(0xFFFFFFFF), "Light", temp == ThemeOption.LIGHT) { temp = ThemeOption.LIGHT }
                ThemeChip(Color(0xFF2F2B3A), "System",temp == ThemeOption.SYSTEM){ temp = ThemeOption.SYSTEM }
                ThemeChip(Color(0xFFE91E63), "Pink",  temp == ThemeOption.PINK)  { temp = ThemeOption.PINK }
                ThemeChip(Color(0xFF42A5F5), "Sky",   temp == ThemeOption.SKY)   { temp = ThemeOption.SKY }
            }

            Button(onClick = { onApply(temp) }, shape = RoundedCornerShape(16.dp)) {
                Text("Apply")
            }
        }
    }
}


@Composable
private fun ThemeChip(
    color: Color,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        tonalElevation = if (selected) 4.dp else 0.dp,
        shape = RoundedCornerShape(12.dp),
        border = if (selected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .size(56.dp, 40.dp)
            .clickable(onClick = onClick)
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (color != Color.Unspecified) {
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxSize()
                        .then(Modifier)
                        .background(color, RoundedCornerShape(10.dp))
                )
            }
        }
    }
}
