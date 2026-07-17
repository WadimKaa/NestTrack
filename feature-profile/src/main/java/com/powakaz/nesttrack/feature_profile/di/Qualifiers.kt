package com.powakaz.nesttrack.feature_profile.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PublicProfileApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrivateProfileApi