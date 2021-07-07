package com.ahobsu.moti.presentation.ui.question

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentAnswerPhotoBindingImpl
import com.ahobsu.moti.presentation.BaseFragment
import gun0912.tedbottompicker.TedBottomPicker
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.create
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import javax.inject.Singleton


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
                    viewModel.setAnswerImage(it)
                }
        }
        viewModel.backPressed.observe(viewLifecycleOwner) {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        viewModel.complete.observe(viewLifecycleOwner) {
            (activity as MissionActivity).replaceFragment(AnswerCompleteFragment.newInstance())
        }
    }
}