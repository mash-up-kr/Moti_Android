package com.ahobsu.moti.presentation.ui.album

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.ActivityAnswersBinding
import com.ahobsu.moti.presentation.BaseActivity
import com.ahobsu.moti.presentation.ui.album.adapter.AnswersAdapter


class AnswersActivity :
    BaseActivity<ActivityAnswersBinding>(R.layout.activity_answers) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore, AnswersViewModelFactory(
            Injection.provideAnswerRepository()
        )
        ).get(AnswersViewModel::class.java)
    }

    private val missionAdapter by lazy { AnswersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        val answerListId = intent.getIntExtra("answerListId", 0)

        if (answerListId == 0) {
            viewModel.initItem(intent.getIntExtra("answerId", 0))
            binding.ciAnswer.visibility = View.GONE
        } else {
            viewModel.initItemList(answerListId)
            binding.ciAnswer.createDotPanel(
                intent.getIntExtra("answerListSize", 0),
                R.drawable.indicator_dot_off,
                R.drawable.indicator_dot_on,
                0)
        }

        initRecyclerView()

        viewModel.backPressed.observe(this) {
            onBackPressed()
        }

        viewModel.diaryAnswerList.observe(this) { it ->
            missionAdapter.replaceAll(it)
        }
    }

    private fun initRecyclerView() {
        with(binding.answersViewPager) {
            offscreenPageLimit = 6
            adapter = missionAdapter
            registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewModel.selectPosition(position)
                    binding.ciAnswer.selectDot(position)
                }
            })
        }

    }
}