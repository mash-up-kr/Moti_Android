package com.ahobsu.moti.presentation.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentHomeBeforeBinding
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.presentation.ui.main.MainActivity

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

        viewModel.todayAnswer.observe(viewLifecycleOwner) {
            (activity as MainActivity).changeFragment(HomeAfterFragment.newInstance())
        }
    }

    override fun onResume() {
        viewModel.checkToday()
        super.onResume()
    }

    companion object {
        fun newInstance() = HomeBeforeFragment()
    }

}