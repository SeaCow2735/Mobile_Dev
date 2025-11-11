package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemePreferences(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme_option")
    }

    val themeFlow: Flow<ThemeOption> = dataStore.data.map { prefs ->
        val saved = prefs[THEME_KEY]
        ThemeOption.values().firstOrNull { it.name == saved } ?: ThemeOption.SYSTEM
    }

    suspend fun setTheme(option: ThemeOption) {
        dataStore.edit { prefs -> prefs[THEME_KEY] = option.name }
    }
}
