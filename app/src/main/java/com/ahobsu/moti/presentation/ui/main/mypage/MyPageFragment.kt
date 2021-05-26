package com.ahobsu.moti.presentation.ui.main.mypage

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentMypageBinding
import com.ahobsu.moti.presentation.BaseFragment


class MyPageFragment :
    BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            MyPageViewModelFactory(Injection.provideAnswerRepository())
        ).get(MyPageViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        fun newInstance() =
            MyPageFragment()
    }

}