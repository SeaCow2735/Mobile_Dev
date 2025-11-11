package com.example.datastore

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PinkScheme = lightColorScheme(
    primary = Color(0xFFE91E63),
    onPrimary = Color.White,
    secondary = Color(0xFF9C27B0),
    background = Color(0xFFFCE4EC),
    surface = Color(0xFFF8BBD0)
)

private val SkyScheme = lightColorScheme(
    primary = Color(0xFF42A5F5),
    onPrimary = Color.White,
    secondary = Color(0xFF00BCD4),
    background = Color(0xFFE3F2FD),
    surface = Color(0xFFBBDEFB)
)

@Composable
fun AppTheme(option: ThemeOption, content: @Composable () -> Unit) {
    val useDark = when (option) {
        ThemeOption.SYSTEM -> isSystemInDarkTheme()
        ThemeOption.DARK   -> true
        else               -> false
    }

    val scheme = when (option) {
        ThemeOption.SYSTEM -> if (useDark) darkColorScheme() else lightColorScheme()
        ThemeOption.LIGHT  -> lightColorScheme()
        ThemeOption.DARK   -> darkColorScheme()
        ThemeOption.PINK   -> PinkScheme
        ThemeOption.SKY    -> SkyScheme
    }

    MaterialTheme(colorScheme = scheme, content = content)
}
