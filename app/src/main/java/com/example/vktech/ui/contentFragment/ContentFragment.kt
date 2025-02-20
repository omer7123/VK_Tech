package com.example.vktech.ui.contentFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.vktech.App
import com.example.vktech.R
import com.example.vktech.databinding.FragmentContentBinding
import com.example.vktech.presentation.contentFragment.ContentScreenState
import com.example.vktech.presentation.contentFragment.ContentViewModel
import com.example.vktech.presentation.multiViewModelFactory.MultiViewModelFactory
import com.example.vktech.util.getAppComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding: FragmentContentBinding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory
    private val viewModel: ContentViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ContentViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(layoutInflater)

        viewModel.screenState.onEach { state ->
            renderState(state)
        }.launchIn(lifecycleScope)

        return binding.root
    }

    private fun renderState(state: ContentScreenState) {
        when(state){
            is ContentScreenState.Content -> Log.e("Success", "OLO")
            is ContentScreenState.Error -> Log.e("ERROR", state.msg)
            ContentScreenState.Initial -> Log.e("INIT", "OLO")
            ContentScreenState.Loading -> Log.e("LOADING", "OLO")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}