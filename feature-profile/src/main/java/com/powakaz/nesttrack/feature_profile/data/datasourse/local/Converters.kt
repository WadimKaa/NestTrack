package com.powakaz.nesttrack.feature_profile.data.datasourse.local

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromSyncState(state: SyncState): String = state.name

    @TypeConverter
    fun toSyncState(state: String): SyncState = SyncState.valueOf(state)
}