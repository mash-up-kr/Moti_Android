package com.ahobsu.moti.presentation.ui.question

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.ActivityQuestionSelectBinding
import com.ahobsu.moti.domain.MissionUseCase
import com.ahobsu.moti.presentation.BaseActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class QuestionSelectActivity :
    BaseActivity<ActivityQuestionSelectBinding>(R.layout.activity_question_select) {

    private val viewModel by viewModels<QuestionViewModel>()
    private val questionAdapter by lazy { QuestionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        initRecyclerView()
        MissionUseCase(Injection.provideMissionRepository()).getMissions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.e("getMissions ", it.toString())
                val items = it.map {
                    QuestionItemViewModel(
                        id = it.id,
                        title = it.title,
                        isContent = it.isContent,
                        isImage = it.isImage
                    )
                }
                questionAdapter.replaceAll(items)

            }, { e ->
                Log.e("e", e.toString())
            })


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