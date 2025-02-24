package com.example.vktech.presentation.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vktech.R
import com.example.vktech.data.core.result.Resource
import com.example.vktech.domain.useCases.GetVideoByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val getVideoByIdUseCase: GetVideoByIdUseCase
) : ViewModel() {

    private val _stateScreen: MutableStateFlow<PlayerScreenState> =
        MutableStateFlow(PlayerScreenState.Initial)
    val stateScreen: StateFlow<PlayerScreenState> get() = _stateScreen

    fun getVideoById(videoId: Int) {
        viewModelScope.launch {
            getVideoByIdUseCase(videoId).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _stateScreen.value = if (resource.responseCode == 0){
                            PlayerScreenState.Error(R.string.internet_error)
                        }else{
                            PlayerScreenState.Error(R.string.unknown_error)
                        }
                    }

                    Resource.Loading -> _stateScreen.value = PlayerScreenState.Loading
                    is Resource.Success -> _stateScreen.value =
                        PlayerScreenState.Content(resource.data)
                }
            }
        }
    }

    fun saveState(currentPosition: Long, isPlaying: Boolean) {
        _stateScreen.update { state ->
            if (state is PlayerScreenState.Content) {
                PlayerScreenState.Content(
                    data = state.data,
                    currentPosition = currentPosition,
                    isPlaying = isPlaying
                )
            } else {
                state
            }
        }
    }
}