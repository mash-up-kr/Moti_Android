package com.ahobsu.moti.presentation.ui.album

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentAlbumBinding
import com.ahobsu.moti.presentation.BaseFragment


class AlbumFragment :
    BaseFragment<FragmentAlbumBinding>(R.layout.fragment_album) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            AlbumViewModelFactory(Injection.provideAnswerRepository())
        ).get(AlbumViewModel::class.java)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        binding.scrollViewAlbumTop.setOnTouchListener { v, event ->
            true
        }

        binding.tvAlvum.setOnClickListener {
            binding.scrollViewAlbumTop.scrollTo((Math.random() * 1800).toInt(), 0)
        }
    }

    companion object {
        fun newInstance() =
            AlbumFragment()
    }

}