package com.ahobsu.moti.presentation.ui.question

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentAnswerShortPhotoBindingImpl
import com.ahobsu.moti.presentation.BaseFragment


class AnswerShortFragment :
    BaseFragment<FragmentAnswerShortPhotoBindingImpl>(R.layout.fragment_answer_short_photo) {

    companion object {
        const val ID = "id"
        fun newInstance(id: Int) = AnswerShortFragment().apply {
            arguments = Bundle().apply {
                putInt(ID, id)
            }
        }
    }

    private val missionId by lazy { requireArguments().getInt(ID) }

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore, MissionViewModelFactory(
                Injection.provideMissionRepository()
            )
        ).get(MissionViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.getMission(missionId)
    }

}