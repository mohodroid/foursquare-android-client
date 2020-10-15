package com.mohdroid.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val builder = chain?.request()?.newBuilder()
        builder?.addHeader("client_id", "WLRGPJSQWJEX4ZVXESOLKYAUJHQRPAGFR3ORNYUIAZSWUF0X")
        builder?.addHeader("client_secret", "WLXU2U1CTV3TARXNY4CRE3F03NBZFOH0HJCGZJ2N4LCVXCBI")
        builder?.addHeader("v", "20111218")
        builder?.addHeader("ll", "40.7463956,-73.9852992")

        builder?.addHeader("Content-Type", "application/json")
        val newRequest = builder?.build()
        return chain?.proceed(newRequest!!)!!
    }
}