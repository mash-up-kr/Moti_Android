package com.ahobsu.moti.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ahobsu.moti.BaseActivity
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ActivityLoginBinding
import com.ahobsu.moti.main.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMainDataBinding()

        loginViewModel.signUpFragment.observe(this, Observer {
            if (it == LoginViewModel.SignUpFragment.NickName) {
                changeFragment(SignUpNicknameFragment.newInstance())
            }
        })
    }

    fun startMainActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fl_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    fun popFragment() {
        supportFragmentManager.popBackStack()
    }

    private fun initMainDataBinding() {
        binding.viewModel = loginViewModel
    }
}
