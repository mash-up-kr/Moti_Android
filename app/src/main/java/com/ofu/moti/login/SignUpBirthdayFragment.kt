package com.ofu.moti.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ofu.moti.BaseFragment
import com.ofu.moti.R
import com.ofu.moti.databinding.FragmentLoginBirthdayBinding

class SignUpBirthdayFragment :
    BaseFragment<FragmentLoginBirthdayBinding>(R.layout.fragment_login_birthday) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.signUpFragment.observe(
            viewLifecycleOwner,
            Observer<LoginViewModel.SignUpFragment> {
                viewModel.signUpFragment.postValue(LoginViewModel.SignUpFragment.EXIT)
                (activity as LoginActivity?)?.changeFragment(SignUpCompleteFragment.newInstance())
            })
    }

    companion object {
        fun newInstance() = SignUpBirthdayFragment()
    }

}