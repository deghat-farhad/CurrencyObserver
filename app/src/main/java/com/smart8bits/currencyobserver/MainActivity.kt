package com.smart8bits.currencyobserver

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okio.ByteString

class MainActivity : AppCompatActivity() {
    private var start: Button? = null
    private var output: TextView? = null
    private var client: OkHttpClient? = null

    private val NORMAL_CLOSURE_STATUS = 1000

    private inner class EchoWebSocketListener : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            output("Receiving : $text")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            output("Receiving bytes : " + bytes.hex())
        }

        override fun onClosing(
            webSocket: WebSocket,
            code: Int,
            reason: String
        ) {
            webSocket.close(
                NORMAL_CLOSURE_STATUS,
                null
            )
            output("Closing : $code / $reason")
        }

        override fun onFailure(
            webSocket: WebSocket,
            t: Throwable,
            response: Response?
        ) {
            output("Error : " + t.message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start = findViewById<View>(R.id.start) as Button
        output = findViewById<View>(R.id.output) as TextView
        client = OkHttpClient()
        start!!.setOnClickListener { start() }
    }

    private fun start() {
        val request =
            Request.Builder().url("wss://stream.binance.com:9443/ws/btcusdt@trade")
                .build()
        val listener = EchoWebSocketListener()
        client!!.newWebSocket(request, listener)
        client!!.dispatcher.executorService.shutdown()
    }

    private fun output(txt: String) {
        runOnUiThread {
            output!!.text = """
     ${output!!.text}
     
     $txt
     """.trimIndent()
        }
    }
}