package com.example.vktech.di

import android.content.Context
import com.example.vktech.ui.MainActivity
import com.example.vktech.ui.content.ContentFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PresentationModule::class,
        DomainModule::class,
        DataModule::class,
        DataSourceModule::class,
    ]
)
interface AppComponent{

    fun inject(fragment: ContentFragment)
    fun inject(activity: MainActivity)
    fun apiUrlProvider(): ApiUrlProvider

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}