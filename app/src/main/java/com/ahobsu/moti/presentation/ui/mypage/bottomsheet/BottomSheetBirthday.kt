package com.ahobsu.moti.presentation.ui.mypage.bottomsheet

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.BR
import com.ahobsu.moti.R
import com.ahobsu.moti.presentation.cuscomview.DateSpinnerCustomView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetBirthday : BottomSheetDialogFragment() {

    lateinit var binding: ViewDataBinding

    var dateSpinnerCustomView: DateSpinnerCustomView? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModelFactory =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(
            requireActivity(), viewModelFactory
        ).get(MyPageEditDialogViewModel::class.java)
        binding.setVariable(BR.viewModel, viewModel)

        dateSpinnerCustomView = view?.findViewById(R.id.date_picker_my_page)
    }

    override fun onCancel(dialog: DialogInterface) {
        dateSpinnerCustomView?.getDatePicker()?.let { onClickItemListener?.onClickItemListener(it) }
        super.onCancel(dialog)
    }

    interface OnClickItemListener {
        fun onClickItemListener(data: String)
    }

    private var onClickItemListener: OnClickItemListener? = null

    fun setOnClickItemListener(onClickItemListener: OnClickItemListener) {
        this.onClickItemListener = onClickItemListener
    }

    lateinit var viewModel: MyPageEditDialogViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.dialog_bottom_sheet_birthday, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}