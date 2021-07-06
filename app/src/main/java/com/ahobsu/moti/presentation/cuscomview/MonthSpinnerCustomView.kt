package com.ahobsu.moti.presentation.cuscomview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ahobsu.moti.databinding.CustomMonthSpinnerBinding

class MonthSpinnerCustomView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(
    context, attrs, defStyleAttr
) {

    private lateinit var viewBinding: CustomMonthSpinnerBinding

    init {
        initLayout()
        initDatePicker()
    }

    private fun initLayout() {
        viewBinding = CustomMonthSpinnerBinding.inflate(LayoutInflater.from(context), this)
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

    fun setMonthPicker(date: String) {
        //"yyyy.MM"
        val dateYYMM = date.split(".")
        viewBinding.pickerYear.value = dateYYMM[0].toInt()
        viewBinding.pickerMonth.value = dateYYMM[1].toInt()

    }

    fun getMonthPicker(): List<Int> {
        return listOf<Int>(viewBinding.pickerYear.value, viewBinding.pickerMonth.value)
    }
}