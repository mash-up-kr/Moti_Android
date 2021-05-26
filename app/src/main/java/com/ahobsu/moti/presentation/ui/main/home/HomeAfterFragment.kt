package com.ahobsu.moti.presentation.ui.main.home

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.core.view.marginBottom
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentHomeAfterBinding
import com.ahobsu.moti.presentation.BaseFragment

class HomeAfterFragment :
    BaseFragment<FragmentHomeAfterBinding>(R.layout.fragment_home_after) {

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
            HomeAfterFragment()
    }

}