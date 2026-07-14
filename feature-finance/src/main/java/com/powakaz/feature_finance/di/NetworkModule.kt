package com.powakaz.feature_finance.di

import com.powakaz.core_network.di.NetworkModule
import com.powakaz.feature_finance.data.remote.NetworkFinanceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @PublicNetworkFinanceApi
    @Singleton
    fun providePublicNetworkFinanceApi(@NetworkModule.PublicClient retrofit: Retrofit) : NetworkFinanceApi{
        return retrofit.create(NetworkFinanceApi::class.java)
    }


    @Provides
    @AuthenticatedNetworkFinanceApi
    @Singleton
    fun providePrivateNetworkFinanceApi(@NetworkModule.AuthenticatedClient retrofit: Retrofit) : NetworkFinanceApi{
        return retrofit.create(NetworkFinanceApi::class.java)
    }


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PublicNetworkFinanceApi


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthenticatedNetworkFinanceApi
}