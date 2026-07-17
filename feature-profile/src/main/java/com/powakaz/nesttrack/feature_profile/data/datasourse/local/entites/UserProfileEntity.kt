package com.powakaz.nesttrack.feature_profile.data.datasourse.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.SyncState
import java.time.LocalDate

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val birthDate: LocalDate?,
    val avatarUrl: String?,
    val createdAt: LocalDate?,
    val syncState: SyncState
)