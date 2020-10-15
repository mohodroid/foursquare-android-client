package com.mohdroid.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import java.nio.charset.Charset

class BodyLogger: Interceptor {
    override fun intercept(chain: Interceptor.Chain?): okhttp3.Response {
        val request = chain?.request()
        Log.i("Retrofit", "call ==> " + request?.url())
        val response = chain?.proceed(request!!)
        val responseBody = response?.body()
        val source = responseBody!!.source()
        source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
        val buffer = source.buffer()
        val strResponse = buffer.clone().readString(Charset.forName("UTF8")).toString()
        Log.i("Retrofit", "status code : ${response.code()} and ret ==> " + strResponse)
        println(strResponse)
        return response
    }
}