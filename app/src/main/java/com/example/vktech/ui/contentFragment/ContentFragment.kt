package com.example.vktech.ui.contentFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.vktech.App
import com.example.vktech.R
import com.example.vktech.databinding.FragmentContentBinding
import com.example.vktech.presentation.contentFragment.ContentViewModel
import com.example.vktech.presentation.multiViewModelFactory.MultiViewModelFactory
import com.example.vktech.util.getAppComponent
import javax.inject.Inject

class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding: FragmentContentBinding = requireNotNull(_binding)

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


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screeenState.observe(viewLifecycleOwner){
            Log.e("Screen state", it.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}