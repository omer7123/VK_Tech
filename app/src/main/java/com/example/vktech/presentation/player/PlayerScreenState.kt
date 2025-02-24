package com.example.vktech.presentation.player

import com.example.vktech.domain.entity.VideoInfoEntity

sealed interface PlayerScreenState {
    data class Error(val msgId: Int) : PlayerScreenState
    data class Content(
        val data: VideoInfoEntity,
        val currentPosition: Long = 0L,
        val isPlaying: Boolean = true
    ) : PlayerScreenState

    data object Initial : PlayerScreenState
    data object Loading : PlayerScreenState
}