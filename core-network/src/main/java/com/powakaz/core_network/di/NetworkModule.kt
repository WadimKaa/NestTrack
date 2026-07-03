package com.powakaz.core_network.di

import com.powakaz.core_common.manager.SessionManager
import com.powakaz.core_common.repository.TokenRepository
import com.powakaz.core_network.BuildConfig
import com.powakaz.core_network.factory.OkHttpFactory
import com.powakaz.core_network.factory.RetrofitFactory
import com.powakaz.core_network.interceptor.AuthInterceptor
import com.powakaz.core_network.interceptor.SessionInterceptor
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
    fun provideAuthInterceptor(tokenRepository: TokenRepository): AuthInterceptor {
        return AuthInterceptor(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideSessionInterceptor(sessionManager: SessionManager): SessionInterceptor {
        return SessionInterceptor(sessionManager)
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
    fun provideAuthenticatedOkHttpClient(
        authInterceptor: AuthInterceptor,
        sessionInterceptor: SessionInterceptor
    ): OkHttpClient {
        return OkHttpFactory.createOkHttpClient(authInterceptor, sessionInterceptor)
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