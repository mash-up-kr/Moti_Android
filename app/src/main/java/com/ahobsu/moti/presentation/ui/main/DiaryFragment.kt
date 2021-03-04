package com.ahobsu.moti.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.FragmentAlbumBinding
import com.ahobsu.moti.databinding.FragmentDiaryBinding
import com.ahobsu.moti.databinding.FragmentHomeBinding
import com.ahobsu.moti.presentation.BaseFragment


class DiaryFragment :
    BaseFragment<FragmentDiaryBinding>(R.layout.fragment_diary) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        fun newInstance() = DiaryFragment()
    }

}