package com.ahobsu.moti.presentation.ui.question

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentAnswerCompleteBindingImpl
import com.ahobsu.moti.presentation.BaseFragment


class AnswerCompleteFragment :
    BaseFragment<FragmentAnswerCompleteBindingImpl>(R.layout.fragment_answer_complete) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore, MissionViewModelFactory(
                Injection.provideMissionRepository(),
                Injection.provideAnswerRepository()
            )
        ).get(MissionViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        fun newInstance() = AnswerCompleteFragment()
    }
}