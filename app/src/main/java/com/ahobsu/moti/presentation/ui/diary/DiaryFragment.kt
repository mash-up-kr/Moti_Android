package com.ahobsu.moti.presentation.ui.diary

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentDiaryBinding
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.presentation.ui.album.AnswersActivity
import com.ahobsu.moti.presentation.ui.diary.adapter.DiaryAdapter
import com.ahobsu.moti.presentation.ui.diary.model.DiaryItemModel
import com.ahobsu.moti.presentation.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.*


class DiaryFragment :
    BaseFragment<FragmentDiaryBinding>(R.layout.fragment_diary) {

    private var listSize = 0
    private var isRenewable = true
    private var isRenewableTop = true
    private var isRenewableBottom = true

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            DiaryViewModelFactory(Injection.provideAnswerRepository())
        ).get(DiaryViewModel::class.java)
    }

    private val diaryAdapter by lazy {
        DiaryAdapter().apply {
            setOnItemClickListener(object : DiaryAdapter.OnItemClickListener {
                override fun onItemClick(item: DiaryItemModel) {
                    val intent = Intent(activity, AnswersActivity::class.java)
                    intent.putExtra("answerId", item.answerId)
                    startActivity(intent)

                    if (item.isContent)
                        Log.e("onItemClick", "id  $id")
                }
            })
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        initRecyclerView()
        viewModel.initAnswersDays()

        val date = Calendar.getInstance()
        val datetime = SimpleDateFormat("yyyy.MM.DD", Locale.KOREA).format(date.time)
        onChangeCalenderDate(datetime, isToday = true)

        binding.diaryRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isRenewable) {
                    val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    if (isRenewableBottom && lastVisibleItemPosition + 1 == listSize) {
                        viewModel.onScrollEvent(false)
                        isRenewable = false

                    }
                    if (isRenewableTop && lastVisibleItemPosition <= 3) {
                        viewModel.onScrollEvent(true)
                        isRenewable = false
                    }
                }
            }
        })

        viewModel.diaryList.observe(viewLifecycleOwner) {
            diaryAdapter.submitList(it)
            listSize = it.size
            isRenewable = true
        }
        viewModel.selectedCalender.observe(viewLifecycleOwner) {
            (activity as MainActivity).addSelectedCalenderFragment()
        }
        viewModel.writeDayList.observe(viewLifecycleOwner) {
            diaryAdapter.setWriteDayList(it)
        }

        viewModel.isRenewableTop.observe(viewLifecycleOwner) {
            isRenewableTop = it
            isRenewable = true
        }
        viewModel.isRenewableBottom.observe(viewLifecycleOwner) {
            isRenewableBottom = it
            isRenewable = true
            val deco = SpaceDecoration(200)
            binding.diaryRecyclerView.addItemDecoration(deco)

        }
    }

    inner class SpaceDecoration(private val size: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            if (parent.getChildAdapterPosition(view) == listSize - 1) {
                outRect.bottom = size
            } else {
                outRect.bottom = 0
            }
        }
    }

    fun onChangeCalenderDate(date: String, isToday: Boolean) {
        viewModel.setDate(date, isToday)
    }

    private fun initRecyclerView() {
        binding.diaryRecyclerView.apply {
            adapter = diaryAdapter
        }
    }

    companion object {
        fun newInstance() = DiaryFragment()
    }
}