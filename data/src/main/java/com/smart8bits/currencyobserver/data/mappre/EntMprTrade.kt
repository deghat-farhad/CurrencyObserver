package com.smart8bits.currencyobserver.data.mappre

import com.smart8bits.currencyobserver.data.entity.EntTrade
import com.smart8bits.currencyobserverdomain.model.Trade
import javax.inject.Inject

class EntMprTrade @Inject constructor() {
    fun mapToDomain(from: EntTrade) = Trade(from.p)
}