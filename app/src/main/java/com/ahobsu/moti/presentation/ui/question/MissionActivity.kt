package com.ahobsu.moti.presentation.ui.question

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.ActivityMissionBinding
import com.ahobsu.moti.presentation.BaseActivity
import com.ahobsu.moti.presentation.ui.main.model.MissionItemModel
import com.ahobsu.moti.presentation.ui.question.adapter.MissionAdapter

class MissionActivity :
    BaseActivity<ActivityMissionBinding>(R.layout.activity_mission) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore, MissionViewModelFactory(
                Injection.provideMissionRepository()
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

        viewModel.complete.observe(this) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.question_container, AnswerCompleteFragment.newInstance())
            }.commit()
        }

        viewModel.missionList.observe(this) { it ->
            missionAdapter.replaceAll(it)
        }
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