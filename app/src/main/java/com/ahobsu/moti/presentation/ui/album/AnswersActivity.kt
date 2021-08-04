package com.ahobsu.moti.presentation.ui.album

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
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

        viewModel.initItemList(answerListId)
        initRecyclerView()

        viewModel.backPressed.observe(this) {
            onBackPressed()
        }
        viewModel.diaryAnswerList.observe(this) { it ->
            missionAdapter.replaceAll(it)
        }
    }

    private fun initRecyclerView() {
        with(binding.viewPager) {
            offscreenPageLimit = 6
            adapter = missionAdapter
        }
    }
}