package com.example.photosproject.di.component

import android.content.Context
import com.example.photosproject.MyApplication
import com.example.photosproject.di.module.AppModule
import com.example.photosproject.di.module.RemoteModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RemoteModule::class
    ]
)
interface ApplicationComponent {

    fun inject(app: MyApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}