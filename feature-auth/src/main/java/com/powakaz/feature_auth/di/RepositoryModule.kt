package com.powakaz.feature_auth.di

import com.powakaz.feature_auth.data.repository.TokenCheckImpl
import com.powakaz.feature_auth.domain.repository.TokenCheckRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTokenCheckRepository(impl : TokenCheckImpl) : TokenCheckRepository
}