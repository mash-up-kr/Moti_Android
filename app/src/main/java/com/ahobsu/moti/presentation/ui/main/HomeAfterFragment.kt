package com.ahobsu.moti.presentation.ui.main

import android.os.Bundle
import com.ahobsu.moti.R
import androidx.fragment.app.viewModels
import com.ahobsu.moti.databinding.FragmentHomeAfterBinding
import com.ahobsu.moti.presentation.BaseFragment


class HomeAfterFragment :
    BaseFragment<FragmentHomeAfterBinding>(R.layout.fragment_home_after) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        fun newInstance() = HomeAfterFragment()
    }

}