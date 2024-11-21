package com.ayush.dietsnap

import android.app.Application
import com.ayush.dietsnap.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class DietSnapApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DietSnapApplication)
            modules(appModule)
        }
    }
}