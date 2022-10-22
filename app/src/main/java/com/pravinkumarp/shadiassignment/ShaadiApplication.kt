package com.pravinkumarp.shadiassignment

import android.app.Application
import com.pravinkumarp.shadiassignment.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShaadiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShaadiApplication)
            modules(roomModule)
            modules(dataSourceModule)
            modules(repositoryModule)
            modules(viewModelModule)
            modules(networkModule)
        }
    }
}