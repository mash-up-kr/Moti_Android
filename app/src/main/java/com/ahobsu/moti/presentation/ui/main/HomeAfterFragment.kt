package com.ahobsu.moti.presentation.ui.main

import android.os.Bundle
import com.ahobsu.moti.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentHomeAfterBinding
import com.ahobsu.moti.presentation.BaseFragment


class HomeAfterFragment :
    BaseFragment<FragmentHomeAfterBinding>(R.layout.fragment_home_after) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore, MainViewModelFactory(Injection.provideAnswerRepository())
        ).get(MainViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        fun newInstance() = HomeAfterFragment()
    }

}