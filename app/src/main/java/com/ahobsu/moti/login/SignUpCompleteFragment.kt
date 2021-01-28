package com.ahobsu.moti.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ahobsu.moti.BaseFragment
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.FragmentSignupCompleteBinding

class SignUpCompleteFragment :
    BaseFragment<FragmentSignupCompleteBinding>(R.layout.fragment_signup_complete) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.signUpFragment.observe(
            viewLifecycleOwner, Observer<LoginViewModel.SignUpFragment> {
            if (it == LoginViewModel.SignUpFragment.EXIT) {
                (activity as LoginActivity?)?.startMainActivity()
                }
            })
        viewModel.popFragment.observe(viewLifecycleOwner, Observer<Unit> {
            (activity as LoginActivity?)?.popFragment()
        })
    }

    companion object {
        fun newInstance() = SignUpCompleteFragment()
    }

}