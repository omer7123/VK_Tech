package com.example.vktech.presentation.content

import com.example.vktech.domain.entity.VideoInfoEntity

sealed interface ContentScreenState {
    data class Content(val data: List<VideoInfoEntity>) : ContentScreenState
    data object Loading : ContentScreenState
    data object Initial : ContentScreenState
    data class Error(val msgId: Int) : ContentScreenState
}