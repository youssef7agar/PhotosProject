package com.example.photosproject.di.component

import android.content.Context
import com.example.photosproject.MainActivity
import com.example.photosproject.MyApplication
import com.example.photosproject.di.module.AppModule
import com.example.photosproject.di.module.RemoteModule
import com.example.photosproject.di.module.ViewModelModule
import com.example.photosproject.presentation.view.UserFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RemoteModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(app: MyApplication)

    fun inject(activity: MainActivity)

    fun inject(usersFragment: UserFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}