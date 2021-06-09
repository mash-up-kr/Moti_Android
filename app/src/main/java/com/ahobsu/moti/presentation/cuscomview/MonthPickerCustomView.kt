package com.ahobsu.moti.presentation.cuscomview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ahobsu.moti.databinding.CustomMonthPickerBinding

class MonthPickerCustomView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(
    context, attrs, defStyleAttr
) {

    private lateinit var viewBinding: CustomMonthPickerBinding

    init {
        initLayout()
        initDatePicker()
    }

    private fun initLayout() {
        viewBinding = CustomMonthPickerBinding.inflate(LayoutInflater.from(context), this)
    }

    private fun initDatePicker() {
        viewBinding.pickerYear.maxValue = 2021
        viewBinding.pickerYear.minValue = 1920

        viewBinding.pickerMonth.maxValue = 12
        viewBinding.pickerMonth.minValue = 1

        viewBinding.pickerYear.wrapSelectorWheel = false
        viewBinding.pickerMonth.wrapSelectorWheel = false

        viewBinding.pickerYear.value = 2000
    }

    private fun setMonthPicker(year: Int, math: Int) {
        viewBinding.pickerYear.value = year
        viewBinding.pickerMonth.value = math

    }

    fun getMonthPicker(): String {
        return "${viewBinding.pickerYear.value}-${viewBinding.pickerMonth.value}"
    }
}