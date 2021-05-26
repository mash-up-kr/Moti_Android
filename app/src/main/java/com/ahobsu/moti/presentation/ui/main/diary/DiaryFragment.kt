package com.ahobsu.moti.presentation.ui.main.diary

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentDiaryBinding
import com.ahobsu.moti.presentation.BaseFragment


class DiaryFragment :
    BaseFragment<FragmentDiaryBinding>(R.layout.fragment_diary) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            DiaryViewModelFactory(Injection.provideAnswerRepository())
        ).get(DiaryViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        fun newInstance() =
            DiaryFragment()
    }

}