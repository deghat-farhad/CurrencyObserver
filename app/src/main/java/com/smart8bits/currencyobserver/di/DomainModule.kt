package com.smart8bits.currencyobserver.di

import com.smart8bits.currencyobserverdomain.repository.TradeRepository
import com.smart8bits.currencyobserverdomain.usecase.getBitcoinUsdtRate.GetBtcUsdtRate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun getBtcUsdtRate(
        @Named("mainThreadScheduler")
        uiScheduler: Scheduler,
        @Named("ioScheduler")
        executionScheduler: Scheduler,
        tradeRepository: TradeRepository
    ) = GetBtcUsdtRate(uiScheduler, executionScheduler, tradeRepository)

    @Provides
    @Named("ioScheduler")
    fun ioScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named("mainThreadScheduler")
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}