package com.example.vktech.di

import android.content.Context
import com.example.vktech.ui.contentFragment.ContentFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresentationModule::class, DomainModule::class])
interface AppComponent{

    fun inject(fragment: ContentFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}