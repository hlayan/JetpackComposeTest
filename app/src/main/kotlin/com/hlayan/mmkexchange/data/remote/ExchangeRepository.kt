package com.hlayan.mmkexchange.data.remote

import com.hlayan.mmkexchange.LatestRates
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

interface ExchangeRepository {

    suspend fun getLatestRates(): LatestRates?

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ExchangeModule {

    @Binds
    abstract fun bindExchangeRepository(
        exchangeRepositoryImpl: ExchangeRepositoryImpl
    ): ExchangeRepository
}