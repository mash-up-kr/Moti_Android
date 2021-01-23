package com.ofu.moti.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ofu.moti.BaseFragment
import com.ofu.moti.R
import com.ofu.moti.databinding.FragmentLoginNicknameBinding


class SignUpNicknameFragment :
    BaseFragment<FragmentLoginNicknameBinding>(R.layout.fragment_login_nickname) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.signUpFragment.observe(
            viewLifecycleOwner, Observer<LoginViewModel.SignUpFragment> {
                if (it == LoginViewModel.SignUpFragment.Gender) {
                    (activity as LoginActivity?)?.changeFragment(SignUpGenderFragment.newInstance())
                }
            })
        viewModel.popFragment.observe(viewLifecycleOwner, Observer<Unit> {
            (activity as LoginActivity?)?.popFragment()
        })
    }


    companion object {
        fun newInstance() = SignUpNicknameFragment()
    }

}