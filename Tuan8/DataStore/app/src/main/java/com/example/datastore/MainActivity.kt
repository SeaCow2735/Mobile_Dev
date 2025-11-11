package com.example.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainActivity : ComponentActivity() {

    private val prefs by lazy { ThemePreferences(applicationContext.themeDataStore) }
    private val vm: ThemeViewModel by viewModels { ThemeViewModel.Factory(prefs) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme by vm.theme.collectAsStateWithLifecycle()
            AppTheme(theme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ThemeScreen(
                        selected = theme,
                        onApply  = { vm.setTheme(it) }
                    )
                }
            }
        }
    }
}
