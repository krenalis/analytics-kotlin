package com.meergo.analytics.next

import android.app.Application
import com.meergo.analytics.kotlin.android.Analytics
import com.meergo.analytics.kotlin.core.*

class MainApplication : Application() {

    lateinit var analytics: Analytics

    override fun onCreate() {
        super.onCreate()

        analytics = Analytics("WRITE_KEY", applicationContext) {
            endpoint = "ENDPOINT"
            collectDeviceId = true
            trackApplicationLifecycleEvents = true
            trackDeepLinks = true
            flushAt = 1
            flushInterval = 0
        }
    }
}