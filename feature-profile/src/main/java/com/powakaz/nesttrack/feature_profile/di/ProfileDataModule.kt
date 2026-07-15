package com.powakaz.nesttrack.feature_profile.di


import com.powakaz.core_network.di.NetworkModule
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.api.ProfileApi
import com.powakaz.nesttrack.feature_profile.data.mapper.AvatarMultipartMapper
import com.powakaz.nesttrack.feature_profile.data.repository.ProfileRepositoryImpl
import com.powakaz.nesttrack.feature_profile.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileDataModule {

    @Provides
    @Singleton
    @PublicProfileApi
    fun provideProfileApiPublic(@NetworkModule.PublicClient retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }



    @Provides
    @Singleton
    @PrivateProfileApi
    fun provideProfileApiPrivate(@NetworkModule.AuthenticatedClient retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        @PublicProfileApi publicApi: ProfileApi,
        @PrivateProfileApi privateApi: ProfileApi,
        avatarMultipartMapper: AvatarMultipartMapper
    ): ProfileRepository {
        return ProfileRepositoryImpl(
            publicApi = publicApi,
            privateApi = privateApi,
            avatarMultipartMapper = avatarMultipartMapper)
    }
}