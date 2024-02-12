package com.kkh.data.api

import com.kkh.data.BuildConfig
import okhttp3.Interceptor

class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request  = chain.request()
           val new = request
            .newBuilder()
            .addHeader("X-Api-Key",BuildConfig.API_KEY)
            .build()

        return chain.proceed(new)
    }

}