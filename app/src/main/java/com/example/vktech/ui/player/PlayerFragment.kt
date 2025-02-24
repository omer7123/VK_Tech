package com.example.vktech.ui.player

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.vktech.R
import com.example.vktech.databinding.FragmentPlayerBinding
import com.example.vktech.domain.entity.VideoInfoEntity
import com.example.vktech.presentation.multiViewModelFactory.MultiViewModelFactory
import com.example.vktech.presentation.player.PlayerScreenState
import com.example.vktech.presentation.player.PlayerViewModel
import com.example.vktech.util.getAppComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class PlayerFragment : Fragment() {
    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val player by lazy {
        ExoPlayer.Builder(requireContext()).build()
    }

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory
    private val viewModel: PlayerViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[PlayerViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().getAppComponent().inject(this)

        val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        if (!isPortrait) {
            enterFullscreen()
        } else {
            exitFullscreen()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(layoutInflater)

        if (viewModel.stateScreen.value is PlayerScreenState.Initial) {
            val videoId = requireArguments().getInt(VIDEO_ID)
            viewModel.getVideoById(videoId)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.player.setShowRewindButton(false)
        binding.player.setShowFastForwardButton(false)
        binding.player.setFullscreenButtonClickListener {
            toggleFullscreen()
        }
        viewModel.stateScreen.onEach { state ->
            renderState(state)
        }.launchIn(lifecycleScope)
    }

    private fun renderState(state: PlayerScreenState) {
        when (state) {
            is PlayerScreenState.Content -> renderContent(
                state.data, state.currentPosition, state.isPlaying
            )

            is PlayerScreenState.Error -> renderError()
            PlayerScreenState.Initial -> Unit
            PlayerScreenState.Loading -> renderLoading()
        }
    }

    private fun renderLoading() {
        binding.player.isVisible = false
        binding.likesTv.isVisible = false
        binding.viewsTv.isVisible = false
        binding.progressCircular.isVisible = true
    }

    private fun renderError() {

    }

    private fun renderContent(data: VideoInfoEntity, currentPosition: Long, isPlaying: Boolean) {
        binding.player.isVisible = true
        binding.likesTv.isVisible = true
        binding.viewsTv.isVisible = true
        binding.progressCircular.isVisible = false

        initPlayer(data.video.url, currentPosition, isPlaying)
        val viewsTitle = getString(R.string.title_views, data.views)
        val likesTitle = getString(R.string.likes_title, data.likes)
        binding.viewsTv.text = viewsTitle
        binding.likesTv.text = likesTitle
    }

    private fun toggleFullscreen() {
        val activity = requireActivity()
        val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        if (isPortrait) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    private fun enterFullscreen() {
        val window = requireActivity().window
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun exitFullscreen() {
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }

    private fun initPlayer(uri: String, currentPosition: Long, isPlaying: Boolean) {
        binding.player.player = player

        player.setMediaItem(MediaItem.fromUri(uri))
        player.seekTo(currentPosition)
        player.playWhenReady = isPlaying
        player.prepare()
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveState(player.currentPosition, player.isPlaying)
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val VIDEO_ID = "VIDEO_ID"
    }
}