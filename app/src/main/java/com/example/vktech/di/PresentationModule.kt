package com.example.vktech.di

import androidx.lifecycle.ViewModel
import com.example.vktech.presentation.content.ContentViewModel
import com.example.vktech.presentation.player.PlayerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {
    @Binds
    @[IntoMap ClassKey(ContentViewModel::class)]
    fun provideContentViewModel(contentViewModel: ContentViewModel): ViewModel

    @Binds
    @[IntoMap ClassKey(PlayerViewModel::class)]
    fun providePlayerViewModel(playerViewModel: PlayerViewModel): ViewModel
}