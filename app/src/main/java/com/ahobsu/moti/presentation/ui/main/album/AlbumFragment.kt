package com.ahobsu.moti.presentation.ui.main.album

import android.os.Bundle
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        fun newInstance() =
            AlbumFragment()
    }

}