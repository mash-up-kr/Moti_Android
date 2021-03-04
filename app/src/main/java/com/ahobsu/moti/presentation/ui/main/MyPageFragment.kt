package com.ahobsu.moti.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.FragmentMypageBinding
import com.ahobsu.moti.presentation.BaseFragment


class MyPageFragment :
    BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        fun newInstance() = MyPageFragment()
    }

}