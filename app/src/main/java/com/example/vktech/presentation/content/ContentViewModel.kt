package com.example.vktech.presentation.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vktech.R
import com.example.vktech.data.core.result.Resource
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.useCases.GetAllLocalVideoUseCase
import com.example.vktech.domain.useCases.GetLatestVideoUseCase
import com.example.vktech.domain.useCases.SaveAllVideoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContentViewModel @Inject constructor(
    private val getLatestVideoUseCase: GetLatestVideoUseCase,
    private val getAllLocalVideoUseCase: GetAllLocalVideoUseCase,
    private val saveAllVideoUseCase: SaveAllVideoUseCase
) : ViewModel() {
    private val _screenState: MutableStateFlow<ContentScreenState> =
        MutableStateFlow(ContentScreenState.Initial)
    val screenState: StateFlow<ContentScreenState> = _screenState

    init {
        getLatestVideo()
    }

    fun getLatestVideo() {
        viewModelScope.launch {
            getLatestVideoUseCase().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        if (resource.responseCode == 0) {
                            _screenState.value =
                                ContentScreenState.Error(R.string.internet_error_local)
                            getLocalVideo()
                        }
                    }

                    Resource.Loading -> _screenState.value = ContentScreenState.Loading
                    is Resource.Success -> {
                        saveAllVideo(resource.data)
                        _screenState.value = ContentScreenState.Content(resource.data.hits)
                    }
                }
            }
        }
    }

    private suspend fun saveAllVideo(data: ContentEntity) {
        saveAllVideoUseCase(data.hits).collect { resource ->
            when (resource) {
                is Resource.Error -> _screenState.value =
                    ContentScreenState.Error(R.string.unknown_error)

                Resource.Loading -> Unit
                is Resource.Success -> Unit
            }
        }
    }

    private suspend fun getLocalVideo() {
        getAllLocalVideoUseCase().collect { resource ->
            when (resource) {
                is Resource.Error -> _screenState.value =
                    ContentScreenState.Error(R.string.unknown_error)

                Resource.Loading -> _screenState.value = ContentScreenState.Loading
                is Resource.Success -> _screenState.value =
                    ContentScreenState.Content(resource.data.reversed())
            }
        }
    }
}