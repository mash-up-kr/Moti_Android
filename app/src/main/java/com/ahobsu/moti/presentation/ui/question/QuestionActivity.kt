package com.ahobsu.moti.presentation.ui.question

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.ActivityQuestionSelectBinding
import com.ahobsu.moti.domain.MissionUseCase
import com.ahobsu.moti.presentation.BaseActivity
import com.ahobsu.moti.presentation.ui.question.adapter.QuestionAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class QuestionActivity :
    BaseActivity<ActivityQuestionSelectBinding>(R.layout.activity_question_select) {

    private val viewModel by lazy{
        ViewModelProvider(
            viewModelStore,QuestionViewModelFactory(
                Injection.provideMissionRepository()
            )
        ).get(QuestionViewModel::class.java)
    }

    private val questionAdapter by lazy { QuestionAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        initRecyclerView()
        viewModel.initQuestion()

        questionAdapter.setOnClickItemListener(object : QuestionAdapter.OnClickItemListener {
            override fun onClickQuestion(id: Int) {
                supportFragmentManager.beginTransaction().apply {
                    add(R.id.question_container, AnswerShortFragment.newInstance())
                    addToBackStack(null)

                }.commit()
            }
        })

        viewModel.questionList.observe(this){it->
            questionAdapter.replaceAll(it)
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
            adapter = questionAdapter

        }
    }

}