package com.example.myloginregister

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStoreManager(private val context: Context) {

    suspend fun setUser(username: String, password: String) {
        context.userDataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
            preferences[PASSWORD_KEY] = password
        }
    }

    suspend fun setSession(session: Boolean) {
        context.userDataStore.edit { preferences ->
            preferences[SESSION_KEY] = session
        }
    }

    fun getUsername(): Flow<String> {
        return context.userDataStore.data.map { preferences ->
            preferences[USERNAME_KEY] ?: "null"
        }
    }

    fun getPassword(): Flow<String> {
        return context.userDataStore.data.map { preferences ->
            preferences[PASSWORD_KEY] ?: "null"
        }
    }

    fun getSession(): Flow<Boolean> {
        return context.userDataStore.data.map { preferences ->
            preferences[SESSION_KEY] ?: false
        }
    }



    companion object {
        private const val DATASTORE_NAME = "user_preferences"

        private val SESSION_KEY = booleanPreferencesKey("session_key")
        private val USERNAME_KEY = stringPreferencesKey("username_key")
        private val PASSWORD_KEY = stringPreferencesKey("password_key")

        private val Context.userDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}