package com.powakaz.core_network.di

import android.os.Build
import com.powakaz.core_network.BuildConfig
import com.powakaz.core_network.factory.OkHttpFactory
import com.powakaz.core_network.factory.RetrofitFactory
import com.powakaz.core_network.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor("")
    }

    @Provides
    @Singleton
    @PublicClient
    fun providePublicOkHttpClient(): OkHttpClient {
        return OkHttpFactory.createOkHttpClient()
    }


    @Provides
    @Singleton
    @AuthenticatedClient
    fun provideAuthenticatedOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpFactory.createOkHttpClient(authInterceptor)


    }

    @Provides
    @Singleton
    @PublicClient
    fun providePublicRetrofit(@PublicClient okHttpClient: OkHttpClient): Retrofit {
        return RetrofitFactory.createRetrofit(BuildConfig.BASE_URL, okHttpClient)
    }

    @Provides
    @Singleton
    @AuthenticatedClient
    fun provideAuthenticatedRetrofit(@AuthenticatedClient okHttpClient: OkHttpClient): Retrofit {
        return RetrofitFactory.createRetrofit(BuildConfig.BASE_URL, okHttpClient)
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthenticatedClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PublicClient


}