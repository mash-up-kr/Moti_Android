package com.ahobsu.moti.presentation.ui.diary

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
import com.ahobsu.moti.presentation.ui.diary.adapter.DiaryAdapter
import com.ahobsu.moti.presentation.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class DiaryFragment :
    BaseFragment<FragmentDiaryBinding>(R.layout.fragment_diary) {

    private var listSize = 0
    private var isRenewable = false
    private var isRenewableTop = true
    private var isRenewableBottom = true

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            DiaryViewModelFactory(Injection.provideAnswerRepository())
        ).get(DiaryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private val diaryAdapter by lazy {
        DiaryAdapter().apply {
            setOnItemClickListener(object : DiaryAdapter.OnItemClickListener {
                override fun onItemClick(id: Int) {
                    //TODO("Not yet implemented")
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
        onChangeCalenderDate(datetime)

        binding.diaryRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isRenewable) {
                    val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                    if (isRenewableBottom && lastVisibleItemPosition + 1 == listSize) {
                        viewModel.onScrollEvent(false)
                        isRenewable = false
                        Log.e("test1", "바닥 lastVisibleItemPosition $lastVisibleItemPosition")
                    }
                    if (isRenewableTop && lastVisibleItemPosition <= 3) {
//                        viewModel.onScrollEvent(true)
                        isRenewable = false
                        Log.e("test2", "천장dd $lastVisibleItemPosition")
                    }
                }
            }
        })

        viewModel.diaryList.observe(viewLifecycleOwner) {
            diaryAdapter.submitList(it)
            listSize = it.size
            Log.e("listSize", " ::: ?  $listSize")
        }
        viewModel.selectedCalender.observe(viewLifecycleOwner) {
            (activity as MainActivity).addSelectedCalenderFragment()
        }
        viewModel.writeDayList.observe(viewLifecycleOwner) {
            diaryAdapter.setWriteDayList(it)
            isRenewable = true
        }

        viewModel.isRenewableTop.observe(viewLifecycleOwner) {
            isRenewableTop = false
            Log.e("바닥 isRenewableTop", " $isRenewableTop")

        }
        viewModel.isRenewableBottom.observe(viewLifecycleOwner) {
            isRenewableBottom = false
            val deco = SpaceDecoration(300)
            binding.diaryRecyclerView.addItemDecoration(deco)
            Log.e("바닥 isRenewableBottom", " $isRenewableBottom")

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
                outRect.bottom += size
            }
        }
    }

    fun onChangeCalenderDate(date: String) {
        viewModel.setDate(date)
    }

    private fun initRecyclerView() {
        val deco = SpaceDecoration(300)
        binding.diaryRecyclerView.apply {
            adapter = diaryAdapter
        }
    }

    companion object {
        fun newInstance() = DiaryFragment()
    }
}