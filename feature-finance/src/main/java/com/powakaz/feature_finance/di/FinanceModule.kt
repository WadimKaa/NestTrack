package com.powakaz.feature_finance.di

import com.powakaz.feature_finance.data.repository.FinanceRepositoryImpl
import com.powakaz.feature_finance.domain.repository.FinanceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class FinanceModule {

    @Binds
    @Singleton
    abstract fun bindFinanceRepository(financeRepositoryImpl: FinanceRepositoryImpl): FinanceRepository
}