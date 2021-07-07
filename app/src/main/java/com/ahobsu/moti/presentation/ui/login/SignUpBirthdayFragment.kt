package com.ahobsu.moti.presentation.ui.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.FragmentSignupBirthdayBinding
import com.ahobsu.moti.presentation.BaseFragment

class SignUpBirthdayFragment :
    BaseFragment<FragmentSignupBirthdayBinding>(R.layout.fragment_signup_birthday) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.signUpFragment.observe(
            viewLifecycleOwner, Observer<LoginViewModel.SignUpFragment> {
                if (it == LoginViewModel.SignUpFragment.Complete) {

                    (activity as LoginActivity?)?.setUserInfo(
                        name = null,
                        gender = null,
                        birthday = binding.datePicker.getDatePicker()
                    )

                    (activity as LoginActivity?)?.changeFragment(LoginViewModel.SignUpFragment.Complete)
                }
            })
        viewModel.popFragment.observe(viewLifecycleOwner, Observer<Unit> {
            (activity as LoginActivity?)?.popFragment()
        })
    }

    companion object {
        fun newInstance() = SignUpBirthdayFragment()
    }

}