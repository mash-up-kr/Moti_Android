package com.ahobsu.moti.presentation.ui.question

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ActivityQuestionSelectBinding
import com.ahobsu.moti.presentation.BaseActivity

class QuestionSelectActivity :
    BaseActivity<ActivityQuestionSelectBinding>(R.layout.activity_question_select) {

    private val viewModel by viewModels<QuestionViewModel>()

    private val questionAdapter by lazy { QuestionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        initRecyclerView()

        questionAdapter.items.add(QuestionItemViewModel(2,"dddd"))
        questionAdapter.items.add(QuestionItemViewModel(2,"dddd"))
        questionAdapter.items.add(QuestionItemViewModel(2,"dddd"))
        questionAdapter.items.add(QuestionItemViewModel(2,"dddd"))
    }

    private fun initRecyclerView() {

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        with(binding.viewPager) {
            adapter = questionAdapter
//            setPageTransformer { page, position ->
//                page.translationX = position * -offsetPx
//            }
        }
    }

}