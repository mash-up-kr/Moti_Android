package com.ofu.moti.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ofu.moti.BaseFragment
import com.ofu.moti.R
import com.ofu.moti.databinding.FragmentLoginCompleteBinding

class SignUpCompleteFragment :
    BaseFragment<FragmentLoginCompleteBinding>(R.layout.fragment_login_complete) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.signUpFragment.observe(
            viewLifecycleOwner,
            Observer<LoginViewModel.SignUpFragment> {
                (activity as LoginActivity?)?.startMainActivity()
            })
    }

    companion object {
        fun newInstance() = SignUpCompleteFragment()
    }

}