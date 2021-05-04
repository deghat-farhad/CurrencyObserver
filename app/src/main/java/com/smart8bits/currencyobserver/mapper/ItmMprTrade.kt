package com.smart8bits.currencyobserver.mapper

import com.smart8bits.currencyobserver.item.ItmTrade
import com.smart8bits.currencyobserverdomain.model.Trade
import javax.inject.Inject

class ItmMprTrade @Inject constructor() {
    fun mapToApp(from: Trade) = ItmTrade(from.price)
}