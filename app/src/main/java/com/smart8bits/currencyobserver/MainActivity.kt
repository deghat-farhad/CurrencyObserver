package com.smart8bits.currencyobserver

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.*

class MainActivity : AppCompatActivity() {
    private var start: Button? = null
    private var stop: Button? = null
    private var output: TextView? = null
    private var client: OkHttpClient? = null

    private val NORMAL_CLOSURE_STATUS = 1000

    lateinit var webSocket: WebSocket

    val gson = Gson()

    private inner class EchoWebSocketListener : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            output(text)
        }

        override fun onFailure(
            webSocket: WebSocket,
            t: Throwable,
            response: Response?
        ) {
            t.stackTrace
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start = findViewById(R.id.start)
        stop = findViewById(R.id.stop)
        output = findViewById<View>(R.id.output) as TextView
        start!!.setOnClickListener { start() }
        stop?.setOnClickListener { stop() }
    }

    private fun start() {
        client = OkHttpClient()
        val request =
            Request.Builder().url("wss://stream.binance.com:9443/ws/btcusdt@trade")
                .build()
        val listener = EchoWebSocketListener()
        webSocket = client!!.newWebSocket(request, listener)
        client!!.dispatcher.executorService.shutdown()
    }

    private fun output(txt: String) {
        println(txt)
        runOnUiThread {
            output!!.text = gson.fromJson(txt, Trade::class.java).p.toString()
        }
    }

    private fun stop() {
        webSocket.close(NORMAL_CLOSURE_STATUS, "")
    }
}