package com.ahobsu.moti.presentation.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.ActivityMainBinding
import com.ahobsu.moti.presentation.BaseActivity
import com.ahobsu.moti.presentation.ui.album.AlbumFragment
import com.ahobsu.moti.presentation.ui.diary.CalendarFragment
import com.ahobsu.moti.presentation.ui.diary.DiaryFragment
import com.ahobsu.moti.presentation.ui.home.HomeAfterFragment
import com.ahobsu.moti.presentation.ui.home.HomeBeforeFragment
import com.ahobsu.moti.presentation.ui.mypage.MyPageFragment


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private var lastTimeBackPressed: Long = 0

    private val mainViewModel by lazy {
        ViewModelProvider(
            viewModelStore, MainViewModelFactory(Injection.provideAnswerRepository())
        ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMainDataBinding()

        mainViewModel.getHomeAnswer()

        mainViewModel.todayAnswer.observe(this) {
            changeFragment(
                if (it) HomeAfterFragment.newInstance()
                else HomeBeforeFragment.newInstance())
        }

        binding.bottomNavi.setOnNavigationItemSelectedListener { it ->
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
                    mainViewModel.getHomeAnswer()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    false
                }
            }
        }
    }

    fun addSelectedCalenderFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.dialog_container, CalendarFragment.newInstance())
        }.commit()
        binding.bottomNavi.visibility = View.GONE
    }

    fun deleteSelectedCalenderFragment() {
        binding.bottomNavi.visibility = View.VISIBLE
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, fragment)
        }.commit()
    }

    private fun initMainDataBinding() {
        binding.mainVM = mainViewModel
    }

    override fun onBackPressed() {
        //프래그먼트 onBackPressedListener사용
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is OnBackPressedListener) {
                (fragment as OnBackPressedListener).onBackPressed()
                return
            }
        }

        //두 번 클릭시 어플 종료
        if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            finish()
            return
        }
        lastTimeBackPressed = System.currentTimeMillis()
        Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
    }

    fun selectCalenderDate(date: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.main_container) as
            DiaryFragment?
        fragment?.onChangeCalenderDate(date)
    }
}