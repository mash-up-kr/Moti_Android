package com.ahobsu.moti.presentation.ui.login

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.FragmentSignupNicknameBinding


class SignUpNicknameFragment :
    BaseFragment<FragmentSignupNicknameBinding>(R.layout.fragment_signup_nickname) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.signUpFragment.observe(
            viewLifecycleOwner, Observer<LoginViewModel.SignUpFragment> {
                if (it == LoginViewModel.SignUpFragment.Gender) {
                    val mInputMethodManager =
                        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    mInputMethodManager.hideSoftInputFromWindow(binding.etNickname.windowToken, 0)

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