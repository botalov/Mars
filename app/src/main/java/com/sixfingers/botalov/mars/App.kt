package com.sixfingers.botalov.mars

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        fun getAppContext(): Context {
            return context
        }
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}