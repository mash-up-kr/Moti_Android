package com.ahobsu.moti.presentation.ui.question

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.ActivityMissionBinding
import com.ahobsu.moti.presentation.BaseActivity
import com.ahobsu.moti.presentation.ui.question.adapter.MissionAdapter
import com.ahobsu.moti.presentation.ui.question.model.MissionItemModel

class MissionActivity :
    BaseActivity<ActivityMissionBinding>(R.layout.activity_mission) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore, MissionViewModelFactory(
                Injection.provideMissionRepository(),
                Injection.provideAnswerRepository()
            )
        ).get(MissionViewModel::class.java)
    }

    private val missionAdapter by lazy { MissionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        initRecyclerView()
        viewModel.initMission()
 
        missionAdapter.setOnClickItemListener(object : MissionAdapter.OnClickItemListener {
            override fun onClickMission(data: MissionItemModel) {
                supportFragmentManager.beginTransaction().apply {
                    if (data.isImage && !data.isContent) {
                        add(R.id.question_container, AnswerPhotoFragment.newInstance(data.id))
                    } else {
                        add(R.id.question_container, AnswerShortFragment.newInstance(data.id))
                    }
                    addToBackStack(null)
                }.commit()
            }
        })

        viewModel.backPressed.observe(this) {
            onBackPressed()
        }

        viewModel.missionList.observe(this) { it ->
            missionAdapter.replaceAll(it)
        }
    }

    fun replaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.question_container, fragment)
        }.commit()
    }

    private fun initRecyclerView() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        with(binding.viewPager) {
            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
            adapter = missionAdapter
        }
    }
}