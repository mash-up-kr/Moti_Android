package com.ahobsu.moti.presentation.ui.question

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentAnswerShortPhotoBindingImpl
import com.ahobsu.moti.presentation.BaseFragment
import gun0912.tedbottompicker.TedBottomPicker


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
                Injection.provideMissionRepository(),
                Injection.provideAnswerRepository()
            )
        ).get(MissionViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.getMission(missionId)
        checkCameraPermission()
        viewModel.getImage.observe(viewLifecycleOwner) {
            TedBottomPicker.with(mContext as FragmentActivity?)
                .show {
                    viewModel.setAnswerImage(it.toString())
                }
        }

        viewModel.answerContent.observe(viewLifecycleOwner) {
            viewModel.setAnswerContent()
        }

        viewModel.backPressed.observe(viewLifecycleOwner) {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        viewModel.complete.observe(viewLifecycleOwner) {
            (activity as MissionActivity).replaceFragment(AnswerCompleteFragment.newInstance())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context;
    }


}