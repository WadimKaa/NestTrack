package com.powakaz.core_network.di

import com.powakaz.core_common.repository.TokenRepository
import com.powakaz.core_datastore.TokenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DatastoreModule {


    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: TokenRepositoryImpl): TokenRepository
}