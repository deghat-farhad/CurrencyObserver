package com.smart8bits.currencyobserverdomain.usecase.getBitcoinUsdtRate

import com.smart8bits.currencyobserverdomain.model.Trade
import com.smart8bits.currencyobserverdomain.repository.TradeRepository
import com.smart8bits.currencyobserverdomain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetBtcUsdtRate(
    uiScheduler: Scheduler,
    executionScheduler: Scheduler,
    private val tradeRepository: TradeRepository
) : ObservableUseCase<Trade>(
    uiScheduler,
    executionScheduler
) {
    override fun buildUseCaseObservable(): Observable<Trade> {
        return tradeRepository.getBtcUsdtRate()
    }
}