package com.ahobsu.moti.presentation.ui.mypage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.BuildConfig
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentMypageBinding
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.presentation.ui.main.MainActivity
import gun0912.tedbottompicker.TedBottomPicker


class MyPageFragment :
    BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            MyPageViewModelFactory(Injection.provideUserRepository())
        ).get(MyPageViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        val versionCode: Int = BuildConfig.VERSION_CODE
        val versionName: String = BuildConfig.VERSION_NAME

        binding.tvMypageVersion.text = "현재 $versionName"
        binding.ivMypageImage.setOnClickListener { view ->
            TedBottomPicker.with(mContext as FragmentActivity?)
                .show {
                    viewModel.setUserProfile(it)
                }
        }

        binding.tvMypageQa.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND)
            email.type = "plain/text"
            val address = arrayOf("heesoo0203@gmail.com")
            email.putExtra(Intent.EXTRA_EMAIL, address)
            email.putExtra(Intent.EXTRA_SUBJECT, "MOTI 문의하기")
            email.putExtra(Intent.EXTRA_TEXT,
                " versionCode: $versionCode - versionName:$versionName")
            startActivity(email)
        }
        binding.btnMypageEdit.setOnClickListener {
            (activity as MainActivity).changeFragment(MyPageEditFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = MyPageFragment()
    }

}