package com.ahobsu.moti.presentation.ui.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.FragmentSignupCompleteBinding
import com.ahobsu.moti.presentation.BaseFragment

class SignUpCompleteFragment :
    BaseFragment<FragmentSignupCompleteBinding>(R.layout.fragment_signup_complete) {

    private val viewModel by viewModels<LoginViewModel>()

    companion object {
        const val NAME = "name"
        fun newInstance(name: String) = SignUpCompleteFragment().apply {
            arguments = Bundle().apply {
                putString(NAME, name)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.setUserInfo(NAME, null, null)
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

}