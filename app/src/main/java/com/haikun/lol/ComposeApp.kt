package com.haikun.lol

import android.app.Application
import com.haikun.lol.koin_module.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComposeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ComposeApp)
            modules(KoinModule.koinModule)
        }
    }
}