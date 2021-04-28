package com.ahobsu.moti.presentation.ui.question

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentAnswerShortPhotoBindingImpl
import com.ahobsu.moti.presentation.BaseFragment


class AnswerShortFragment :
    BaseFragment<FragmentAnswerShortPhotoBindingImpl>(R.layout.fragment_answer_short_photo) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore, QuestionViewModelFactory(
                Injection.provideMissionRepository()
            )
        ).get(QuestionViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        fun newInstance() = AnswerShortFragment()
    }

}