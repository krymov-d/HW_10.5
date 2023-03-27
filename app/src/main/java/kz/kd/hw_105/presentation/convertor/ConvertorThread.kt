package kz.kd.hw_105.presentation.convertor

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log

class ConvertorThread : HandlerThread("Convertor Thread") {
    var handlerConvertor : Handler? = null

    override fun onLooperPrepared() {
        super.onLooperPrepared()

        handlerConvertor = object : Handler(looper) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d("ConvertorThread", currentThread().name)
            }
        }
    }
}