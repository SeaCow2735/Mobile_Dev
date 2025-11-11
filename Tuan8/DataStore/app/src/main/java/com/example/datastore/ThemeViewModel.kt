package com.example.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(private val prefs: ThemePreferences) : ViewModel() {


    val theme: StateFlow<ThemeOption> = prefs.themeFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, ThemeOption.SYSTEM)

    fun setTheme(option: ThemeOption) {
        viewModelScope.launch { prefs.setTheme(option) }
    }

    class Factory(private val prefs: ThemePreferences) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            return ThemeViewModel(prefs) as T
        }
    }
}
