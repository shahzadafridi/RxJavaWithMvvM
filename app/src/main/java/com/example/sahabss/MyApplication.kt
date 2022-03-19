package com.example.sahabss

import android.app.Application
import com.example.sahabss.util.ReleaseTree
import timber.log.Timber

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}