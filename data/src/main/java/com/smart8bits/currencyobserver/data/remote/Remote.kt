package com.smart8bits.currencyobserver.data.remote

import com.google.gson.Gson
import com.smart8bits.currencyobserver.data.entity.EntTrade
import com.smart8bits.currencyobserver.data.mappre.EntMprTrade
import com.smart8bits.currencyobserverdomain.model.Trade
import io.reactivex.Observable
import okhttp3.*
import javax.inject.Inject

class Remote @Inject constructor(
    private val client: OkHttpClient,
    private val gson: Gson,
    private val entMprTrade: EntMprTrade
) {
    companion object {
        private val NORMAL_CLOSURE_STATUS = 1000
    }

    private var webSocket: WebSocket? = null

    fun getBtcUsdtRate(): Observable<Trade> {
        return Observable.create<Trade> { emitter ->
            val request =
                Request.Builder().url("wss://stream.binance.com:9443/ws/btcusdt@trade")
                    .build()
            val listener = object : WebSocketListener() {
                override fun onMessage(webSocket: WebSocket, text: String) {
                    emitter.onNext(
                        entMprTrade.mapToDomain(
                            gson.fromJson(
                                text,
                                EntTrade::class.java
                            )
                        )
                    )
                }

                override fun onFailure(
                    webSocket: WebSocket,
                    t: Throwable,
                    response: Response?
                ) {
                    t.stackTrace
                }
            }
            webSocket = client.newWebSocket(request, listener)
            client.dispatcher.executorService.shutdown()
        }.doOnDispose {
            webSocket?.close(NORMAL_CLOSURE_STATUS, "")
        }
    }
}