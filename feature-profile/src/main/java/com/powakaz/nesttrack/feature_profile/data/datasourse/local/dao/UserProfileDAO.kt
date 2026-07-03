package com.powakaz.nesttrack.feature_profile.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.entites.UserProfileEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserProfileDAO  {
    @Query("SELECT * FROM user_profile WHERE id = :userId")
    fun getUserProfile(userId: Int): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = :userId")
    suspend fun getUserProfileOnce(userId: Int): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(profile: UserProfileEntity)
}