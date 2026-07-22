package com.powakaz.nesttrack.feature_time.di

import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.di.NetworkModule
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.api.TimeTrackingApi
import com.powakaz.nesttrack.feature_time.data.repository.TimeTrackingRepositoryImpl
import com.powakaz.nesttrack.feature_time.domain.repository.TimeTrackingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TimeTrackingDataModule {

    @Provides
    @Singleton
    @PublicTimeTrackingApi
    fun provideTimeTrackingApiPublic(@NetworkModule.PublicClient retrofit: Retrofit): TimeTrackingApi {
        return retrofit.create(TimeTrackingApi::class.java)
    }

    @Provides
    @Singleton
    @PrivateTimeTrackingApi
    fun provideTimeTrackingApiPrivate(@NetworkModule.AuthenticatedClient retrofit: Retrofit): TimeTrackingApi {
        return retrofit.create(TimeTrackingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTimeTrackingRepository(
        @PublicTimeTrackingApi publicApi: TimeTrackingApi,
        @PrivateTimeTrackingApi privateApi: TimeTrackingApi,
        userIdRepository: UserIdRepository
    ): TimeTrackingRepository {
        return TimeTrackingRepositoryImpl(
            publicApi = publicApi,
            privateApi = privateApi,
            userIdRepository = userIdRepository
        )
    }
}