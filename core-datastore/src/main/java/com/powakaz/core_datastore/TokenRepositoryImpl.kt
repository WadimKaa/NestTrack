package com.powakaz.core_datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.powakaz.core_common.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenRepositoryImpl @Inject constructor(private val dataStore : DataStore<Preferences>) : TokenRepository {


    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_ID_KEY = intPreferencesKey("user_id")
    }

    override fun getAccessToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }


    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    override fun getUserId(): Flow<Int?> {
        return dataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }
    }

    override suspend fun saveUserId(userId: Int) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }


}