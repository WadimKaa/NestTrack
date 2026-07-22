package com.powakaz.nesttrack.feature_time.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PublicTimeTrackingApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrivateTimeTrackingApi