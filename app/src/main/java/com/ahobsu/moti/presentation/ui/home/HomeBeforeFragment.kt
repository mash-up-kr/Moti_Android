package com.ahobsu.moti.presentation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentHomeBeforeBinding
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.presentation.ui.main.MainActivity
import com.ahobsu.moti.presentation.ui.question.AnswerCompleteFragment
import com.ahobsu.moti.presentation.ui.question.MissionActivity

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

    override fun onResume() {
        (activity as MainActivity).checkTodayAnswer()
        super.onResume()
    }
    companion object {
        fun newInstance() =
            HomeBeforeFragment()
    }

}