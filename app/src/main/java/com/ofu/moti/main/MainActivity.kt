package com.ofu.moti.main

import android.os.Bundle
import androidx.activity.viewModels
import com.ofu.moti.BaseActivity
import com.ofu.moti.R
import com.ofu.moti.databinding.ActivityMainBinding

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