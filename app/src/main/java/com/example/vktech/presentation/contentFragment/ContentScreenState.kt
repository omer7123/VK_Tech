package com.example.vktech.presentation.contentFragment

import com.example.vktech.domain.entity.ContentEntity

sealed interface ContentScreenState {
    data class Content(val data: ContentEntity) : ContentScreenState
    data object Loading : ContentScreenState
    data object Initial : ContentScreenState
    data class Error(val msg: String) : ContentScreenState
}