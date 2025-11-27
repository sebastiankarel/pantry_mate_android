package de.slapps.pantrymate.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

class AppDataStore(context: Context) {
    private val dataStore = context.dataStore

    val sessionTokenFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[SESSION_TOKEN_KEY]
    }
    val refreshTokenFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN_KEY]
    }

    suspend fun saveTokens(sessionToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[SESSION_TOKEN_KEY] = sessionToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val SESSION_TOKEN_KEY = stringPreferencesKey("session_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }
}
