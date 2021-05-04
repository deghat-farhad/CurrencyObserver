package com.smart8bits.currencyobserver.data.di

import com.smart8bits.currencyobserver.data.repository.TradeRepositoryImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoriesModule::class])
interface DataComponent {
    fun getTradeRepositoryImpl(): TradeRepositoryImpl
}
