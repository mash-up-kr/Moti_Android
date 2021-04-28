package com.ahobsu.moti.presentation.ui.question

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentAnswerPhotoBindingImpl
import com.ahobsu.moti.presentation.BaseFragment


class AnswerPhotoFragment :
    BaseFragment<FragmentAnswerPhotoBindingImpl>(R.layout.fragment_answer_photo) {

    companion object {
        const val ID = "id"
        fun newInstance(id: Int) = AnswerPhotoFragment().apply {
            arguments = Bundle().apply {
                putInt(ID, id)
            }
        }
    }
    private val missionId by lazy { requireArguments().getInt(AnswerShortFragment.ID) }

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