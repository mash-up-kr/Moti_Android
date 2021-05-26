package com.ahobsu.moti.presentation.ui.main.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentHomeBeforeBinding
import com.ahobsu.moti.presentation.BaseFragment

class HomeBeforeFragment :
    BaseFragment<FragmentHomeBeforeBinding>(R.layout.fragment_home_before) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            MainHomeViewModelFactory(Injection.provideAnswerRepository())
        ).get(MainHomeViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.getHomeAnswer()
    }

    companion object {
        fun newInstance() =
            HomeBeforeFragment()
    }

}