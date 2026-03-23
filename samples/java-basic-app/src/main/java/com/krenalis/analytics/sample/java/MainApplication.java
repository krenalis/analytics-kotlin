package com.krenalis.analytics.sample.java;

import android.app.Application;

import com.krenalis.analytics.kotlin.android.AndroidAnalyticsKt;
import com.krenalis.analytics.kotlin.core.Analytics;
import com.krenalis.analytics.kotlin.core.compat.JavaAnalytics;

import kotlin.Unit;

public class MainApplication extends Application {

    JavaAnalytics analytics;

    @Override
    public void onCreate() {
        super.onCreate();

        Analytics client = AndroidAnalyticsKt.Analytics("WRITE_KEY", getApplicationContext(), configuration -> {
            configuration.setEndpoint("ENDPOINT");
            configuration.setCollectDeviceId(true);
            configuration.setTrackApplicationLifecycleEvents(true);
            configuration.setFlushAt(1);
            configuration.setFlushInterval(0);
            return Unit.INSTANCE;
        });

        analytics = new JavaAnalytics(client);
    }
}
