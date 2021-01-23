package com.ofu.moti.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ofu.moti.BaseFragment
import com.ofu.moti.R
import com.ofu.moti.databinding.FragmentLoginGenderBinding

class SignUpGenderFragment : BaseFragment<FragmentLoginGenderBinding>(R.layout.fragment_login_gender){

    private val viewModel by viewModels<LoginViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.signUpFragment.observe(
            viewLifecycleOwner,
            Observer<LoginViewModel.SignUpFragment> {
                (activity as LoginActivity?)?.changeFragment(SignUpBirthdayFragment.newInstance())
            })
    }

    companion object {
        fun newInstance() = SignUpGenderFragment()
    }

}