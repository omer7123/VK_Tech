package com.example.vktech.presentation.content

import com.example.vktech.domain.entity.ContentEntity

sealed interface ContentScreenState {
    data class Content(val data: ContentEntity) : ContentScreenState
    data object Loading : ContentScreenState
    data object Initial : ContentScreenState
    data class Error(val msg: String) : ContentScreenState
}