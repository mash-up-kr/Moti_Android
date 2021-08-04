package com.ahobsu.moti.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentMypageBinding
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.presentation.ui.main.MainActivity


class MyPageFragment :
    BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            MyPageViewModelFactory(Injection.provideUserRepository())
        ).get(MyPageViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        //TODO Version 설정
        val version="1.0.0"

        binding.tvMypageQa.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND)
            email.type = "plain/text"
            val address = arrayOf("heesoo0203@gmail.com")
            email.putExtra(Intent.EXTRA_EMAIL, address)
            email.putExtra(Intent.EXTRA_SUBJECT, "MOTI 문의하기")
            email.putExtra(Intent.EXTRA_TEXT, "version: $version")
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