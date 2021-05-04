package com.smart8bits.currencyobserverdomain.repository

import com.smart8bits.currencyobserverdomain.model.Trade
import io.reactivex.Observable

interface TradeRepository {
    fun getBtcUsdtRate(): Observable<Trade>
}