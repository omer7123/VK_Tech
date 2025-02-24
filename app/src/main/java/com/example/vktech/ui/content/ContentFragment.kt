package com.example.vktech.ui.content

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.vktech.R
import com.example.vktech.databinding.FragmentContentBinding
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.entity.VideoInfoEntity
import com.example.vktech.presentation.content.ContentScreenState
import com.example.vktech.presentation.content.ContentViewModel
import com.example.vktech.presentation.multiViewModelFactory.MultiViewModelFactory
import com.example.vktech.ui.player.PlayerFragment
import com.example.vktech.util.getAppComponent
import com.example.vktech.util.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding: FragmentContentBinding get() = requireNotNull(_binding)

    private val adapter = VideoAdapter(
        onItemClickListener = this::onItemClick
    )

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()

    }

    private fun initListener() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getLatestVideo()
        }

        binding.seriesRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                binding.swipeRefresh.isEnabled = !recyclerView.canScrollVertically(-1)
            }
        })
    }

    private fun initView() {
        binding.seriesRv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
        }
        binding.seriesRv.adapter = adapter
    }

    private fun onItemClick(videoInfoEntity: VideoInfoEntity) {
        findNavController().navigate(
            R.id.action_contentFragment_to_playerFragment,
            bundleOf(PlayerFragment.VIDEO_ID to videoInfoEntity.id)
        )
    }

    private fun renderState(state: ContentScreenState) {
        when(state){
            is ContentScreenState.Content -> {
                renderContent(state.data)
            }
            is ContentScreenState.Error -> {
                requireContext().showToast(state.msg)
            }
            ContentScreenState.Initial -> Unit
            ContentScreenState.Loading -> {
                renderLoading()
            }
        }
    }

    private fun renderLoading() {
        binding.content.isVisible = false
        binding.progress.isVisible = true
    }

    private fun renderContent(data: ContentEntity) {
        binding.progress.isVisible = false
        binding.content.isVisible = true
        adapter.submitList(data.hits)
        binding.swipeRefresh.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}