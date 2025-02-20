package com.example.vktech.presentation.contentFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vktech.domain.useCases.GetMostPopularVideoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ContentViewModel @Inject constructor(
    private val getMostPopularVideoUseCase: GetMostPopularVideoUseCase
): ViewModel() {
    private val _screeenState: MutableLiveData<String> = MutableLiveData("")
    val screeenState: LiveData<String> = _screeenState
}