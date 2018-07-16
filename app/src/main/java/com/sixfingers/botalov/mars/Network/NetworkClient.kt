package com.sixfingers.botalov.mars.Network

import com.sixfingers.botalov.mars.App
import com.sixfingers.botalov.mars.R
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


class NetworkClient {
    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {

        if (retrofit == null) {
            val builder = OkHttpClient.Builder()
            val okHttpClient = builder
                    .writeTimeout(40, TimeUnit.SECONDS)
                    .readTimeout(40, TimeUnit.SECONDS)
                    .build()

            val baseUrl = App.getAppContext().getString(R.string.base_url)

            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

        }


        return retrofit
    }
}