package com.powakaz.nesttrack.feature_profile.data.datasourse.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.dao.UserProfileDAO
import com.powakaz.nesttrack.feature_profile.data.datasourse.local.entites.UserProfileEntity

@TypeConverters(Converters::class)
@Database(entities = [UserProfileEntity::class], version = 1)
abstract class UserProfileDatabase : RoomDatabase() {
    abstract fun profileDao() : UserProfileDAO

}