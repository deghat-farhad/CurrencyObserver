package com.smart8bits.currencyobserver

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smart8bits.currencyobserver.item.ItmTrade
import com.smart8bits.currencyobserver.mapper.ItmMprTrade
import com.smart8bits.currencyobserverdomain.model.Trade
import com.smart8bits.currencyobserverdomain.usecase.base.DefaultObserver
import com.smart8bits.currencyobserverdomain.usecase.getBitcoinUsdtRate.GetBtcUsdtRate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViwMdlMain @Inject constructor(
    private val getBtcUsdtRate: GetBtcUsdtRate,
    private val itmMprTrade: ItmMprTrade
) : ViewModel(), LifecycleObserver {
    val btcUsdtRate by lazy { MutableLiveData<ItmTrade>() }

    fun getBtcUsdt() {
        val observer = object : DefaultObserver<Trade>() {
            override fun onNext(it: Trade) {
                super.onNext(it)
                btcUsdtRate.postValue(itmMprTrade.mapToApp(it))
            }
        }
        getBtcUsdtRate.execute(observer)
    }
}