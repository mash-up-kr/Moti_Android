package com.ahobsu.moti.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ActivityMainBinding
import com.ahobsu.moti.presentation.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMainDataBinding()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, HomeFragment.newInstance())
        }.commit()

        binding.bottomNavi.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mypage -> {
                    changeFragment(MyPageFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.diary -> {
                    changeFragment(DiaryFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.album -> {
                    changeFragment(AlbumFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.home -> {
                    changeFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                else ->  {
                   false
                }
            }
        }
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, fragment)
        }.commit()
    }

    private fun initMainDataBinding() {
        binding.mainVM = mainViewModel
    }
}