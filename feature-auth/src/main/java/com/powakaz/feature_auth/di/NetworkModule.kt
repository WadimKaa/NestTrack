package com.powakaz.feature_auth.di

import com.powakaz.core_network.di.NetworkModule.PublicClient
import com.powakaz.feature_auth.data.remote.NetworkTokenApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkCheckTokenApi(@PublicClient retrofit: Retrofit): NetworkTokenApi {
        return retrofit.create(NetworkTokenApi::class.java)
    }
}