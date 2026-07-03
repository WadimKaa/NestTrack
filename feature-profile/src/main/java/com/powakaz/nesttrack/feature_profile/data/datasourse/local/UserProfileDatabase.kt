package com.powakaz.nesttrack.feature_profile.data.datasourse.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.dao.UserProfileDAO
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.entites.UserProfileEntity

@Database(entities = [UserProfileEntity::class], version = 1)
abstract class UserProfileDatabase : RoomDatabase() {
    abstract fun profileDao() : UserProfileDAO

}