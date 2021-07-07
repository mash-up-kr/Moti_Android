package com.ahobsu.moti.presentation.cuscomview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ahobsu.moti.databinding.CustomDateSpinnerBinding

class DateSpinnerCustomView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(
    context,
    attrs, defStyleAttr
) {

    private lateinit var viewBinding: CustomDateSpinnerBinding

    init {
        initLayout()
        initDatePicker()
    }

    private fun initLayout() {
        viewBinding = CustomDateSpinnerBinding.inflate(LayoutInflater.from(context), this)
    }

    private fun initDatePicker() {
        viewBinding.pickerYear.maxValue = 2021
        viewBinding.pickerYear.minValue = 1920
        viewBinding.pickerMonth.maxValue = 12

        viewBinding.pickerMonth.minValue = 1
        viewBinding.pickerDay.maxValue = 31
        viewBinding.pickerDay.minValue = 1

        viewBinding.pickerYear.wrapSelectorWheel = false
        viewBinding.pickerMonth.wrapSelectorWheel = false
        viewBinding.pickerDay.wrapSelectorWheel = false

        viewBinding.pickerYear.value = 2000
    }

    fun getDatePicker(): String {
        return String.format(
            "%04d-%02d-%02d",
            viewBinding.pickerYear.value,
            viewBinding.pickerMonth.value,
            viewBinding.pickerDay.value
        )
    }
}