package com.powakaz.core_datastore.di

import android.content.Context
import android.content.SyncContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.powakaz.core_common.manager.SessionManager
import com.powakaz.core_common.repository.TokenRepository
import com.powakaz.core_datastore.SessionManagerImpl
import com.powakaz.core_datastore.TokenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun bindTokenRepository(impl: TokenRepositoryImpl): TokenRepository


    @Binds
    @Singleton
    abstract fun bindSessionManager(impl: SessionManagerImpl) : SessionManager


    companion object{
        @Provides
        @Singleton
        fun provideDataStore(@ApplicationContext context: Context) : DataStore<Preferences>{
            return context.dataStore
        }
    }
}