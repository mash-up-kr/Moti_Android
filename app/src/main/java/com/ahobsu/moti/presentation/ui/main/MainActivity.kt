package com.ahobsu.moti.presentation.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import com.ahobsu.moti.presentation.BaseActivity
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.ActivityMainBinding
import com.ahobsu.moti.domain.SiginInUseCase

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMainDataBinding()


    }

    private fun initMainDataBinding() {
        binding.mainVM = mainViewModel
    }
}