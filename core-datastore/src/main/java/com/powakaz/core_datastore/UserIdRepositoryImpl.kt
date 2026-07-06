package com.powakaz.core_datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.powakaz.core_common.repository.UserIdRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserIdRepositoryImpl @Inject constructor(private val dataStore : DataStore<Preferences>) : UserIdRepository {

    companion object {
        private val USER_ID_KEY = intPreferencesKey("user_id")
    }

    override fun getUserId(): Flow<Int> {
        return dataStore.data.map {
            it[USER_ID_KEY] ?: -1
        }
    }

    override suspend fun saveUserId(userId: Int) {
        dataStore.edit {
            it[USER_ID_KEY] = userId
        }
    }

    override suspend fun clearUserId() {
        dataStore.edit {
            it.remove(USER_ID_KEY)
        }
    }
}