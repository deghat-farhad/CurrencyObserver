package com.smart8bits.currencyobserver.di

import com.smart8bits.currencyobserver.data.di.DaggerDataComponent
import com.smart8bits.currencyobserverdomain.repository.TradeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun tradeRepository(): TradeRepository = DaggerDataComponent.create().getTradeRepositoryImpl()
}