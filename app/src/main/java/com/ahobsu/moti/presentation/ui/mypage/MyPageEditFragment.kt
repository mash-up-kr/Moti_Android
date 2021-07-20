package com.ahobsu.moti.presentation.ui.mypage

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentMypageEditBinding
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.presentation.ui.main.MainActivity
import com.ahobsu.moti.presentation.ui.mypage.bottomsheet.BottomSheetBirthday
import com.ahobsu.moti.presentation.ui.mypage.bottomsheet.BottomSheetGender
import com.ahobsu.moti.presentation.ui.util.EnumClass


class MyPageEditFragment : BaseFragment<FragmentMypageEditBinding>(R.layout.fragment_mypage_edit) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            MyPageViewModelFactory(Injection.provideUserRepository())
        ).get(MyPageViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.btnSaveMyPage.observe(viewLifecycleOwner) {
            (activity as MainActivity).changeFragment(MyPageFragment.newInstance())
        }

        viewModel.selectedBirthday.observe(viewLifecycleOwner) {
            val bottomSheet = BottomSheetBirthday()
            activity?.supportFragmentManager?.let { it ->
                bottomSheet.show(it, bottomSheet.tag)

                bottomSheet.setOnClickItemListener(object :
                    BottomSheetBirthday.OnClickItemListener {
                    override fun onClickItemListener(data: String) {
                        viewModel.setUserBirthday(data)
                    }
                })
            }
        }

        viewModel.selectedGender.observe(viewLifecycleOwner) {
            val bottomSheet = BottomSheetGender()
            activity?.supportFragmentManager?.let { it ->
                bottomSheet.show(it, bottomSheet.tag)

                bottomSheet.setOnClickItemListener(object : BottomSheetGender.OnClickItemListener {
                    override fun onClickItemListener(data: String) {
                        viewModel.setUserGender(if (data == EnumClass.Gender.MAN.name) "남" else "여")
                    }
                })
            }
        }
    }

    companion object {
        fun newInstance() = MyPageEditFragment()
    }

}