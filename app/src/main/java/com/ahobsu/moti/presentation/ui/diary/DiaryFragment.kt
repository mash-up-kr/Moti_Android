package com.ahobsu.moti.presentation.ui.diary

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentDiaryBinding
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.presentation.ui.diary.adapter.DiaryAdapter
import com.ahobsu.moti.presentation.ui.home.HomeAfterFragment
import com.ahobsu.moti.presentation.ui.main.MainActivity

class DiaryFragment :
    BaseFragment<FragmentDiaryBinding>(R.layout.fragment_diary) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            DiaryViewModelFactory(Injection.provideAnswerRepository())
        ).get(DiaryViewModel::class.java)
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


        viewModel.diaryList.observe(viewLifecycleOwner){
            diaryAdapter.submitList(it)
        }
        viewModel.selectedCalender.observe(viewLifecycleOwner){
            (activity as MainActivity).addSelectedCalenderFragment()
        }
    }

    private fun initRecyclerView() {
        binding.diaryRecyclerView.adapter = diaryAdapter
    }

    companion object {
        fun newInstance() =
            DiaryFragment()
    }
}