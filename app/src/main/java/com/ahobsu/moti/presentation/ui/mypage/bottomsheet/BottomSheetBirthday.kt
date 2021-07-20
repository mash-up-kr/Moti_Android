package com.ahobsu.moti.presentation.ui.mypage.bottomsheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.BR
import com.ahobsu.moti.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetGender : BottomSheetDialogFragment() {

    lateinit var binding: ViewDataBinding
    lateinit var mContext: Context

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_sheet_gender, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MyPageEditDialogViewModel::class.java)
        binding.setVariable(BR.viewModel, viewModel)

        viewModel.userGender.observe(this, Observer { it ->
            onClickItemListener?.onClickItemListener(it.name)
        })

    }
}