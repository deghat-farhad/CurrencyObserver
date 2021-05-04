package com.smart8bits.currencyobserver.data.repository

import com.smart8bits.currencyobserver.data.remote.Remote
import com.smart8bits.currencyobserverdomain.model.Trade
import com.smart8bits.currencyobserverdomain.repository.TradeRepository
import io.reactivex.Observable
import javax.inject.Inject

class TradeRepositoryImpl @Inject constructor(private val remote: Remote) : TradeRepository {
    override fun getBtcUsdtRate(): Observable<Trade> {
        return remote.getBtcUsdtRate()
    }
}