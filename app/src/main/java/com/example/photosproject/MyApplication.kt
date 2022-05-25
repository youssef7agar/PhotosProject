package com.example.photosproject

import android.app.Application
import com.example.photosproject.di.component.ApplicationComponent
import com.example.photosproject.di.component.DaggerApplicationComponent
import timber.log.Timber

class MyApplication : Application() {

    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        provideAppComponent().inject(this)
        Timber.plant(Timber.DebugTree())
    }

    fun provideAppComponent() = appComponent
}