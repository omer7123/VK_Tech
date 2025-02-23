package com.example.vktech.presentation.content

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vktech.data.core.result.Resource
import com.example.vktech.domain.useCases.GetLatestVideoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContentViewModel @Inject constructor(
    private val getLatestVideoUseCase: GetLatestVideoUseCase
): ViewModel() {
    private val _screenState: MutableStateFlow<ContentScreenState> = MutableStateFlow(ContentScreenState.Initial)
    val screenState: StateFlow<ContentScreenState> = _screenState

    init {
        getLatestVideo()
    }

    fun getLatestVideo(){
        viewModelScope.launch {
            getLatestVideoUseCase().collect{ resource->
                when(resource){
                    is Resource.Error -> _screenState.value = ContentScreenState.Error(resource.msg.toString())
                    Resource.Loading -> _screenState.value = ContentScreenState.Loading
                    is Resource.Success -> {
                        Log.e("Success", resource.data.toString())
                        _screenState.value = ContentScreenState.Content(resource.data)
                    }
                }
            }
        }
    }
}