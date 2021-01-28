package com.ahobsu.moti.main

import android.os.Bundle
import androidx.activity.viewModels
import com.ahobsu.moti.BaseActivity
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ActivityMainBinding

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