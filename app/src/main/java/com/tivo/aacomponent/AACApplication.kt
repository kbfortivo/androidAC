package com.tivo.aacomponent

import android.app.Application
import com.tivo.aacomponent.module.ApplicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AACApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AACApplication)
            modules(ApplicationModule.create())
        }
    }
}