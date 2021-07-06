package com.ahobsu.moti.presentation.ui.diary.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ItemCalendarBinding
import com.ahobsu.moti.presentation.ui.diary.FurangCalendar
import java.text.SimpleDateFormat
import java.util.*

class CalendarAdapter()
    : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    var dataList: ArrayList<Int> = arrayListOf()
    lateinit var furangCalendar: FurangCalendar
    lateinit var date: Date

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar, parent, false)
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(dataList[position].toString())
    }

    inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: ItemCalendarBinding? = androidx.databinding.DataBindingUtil.bind(itemView)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(item: String) {
            binding?.model = item


            val firstDateIndex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead - 1

            // 오늘 날짜 처리 - 오늘날짜 date long 받아서 이번달인지 보기
            // val dateString: String = SimpleDateFormat("d", Locale.KOREA).format(date)

            binding?.tvCalenderDays?.let {
//                if (item == dateString) {
//                    it.background= itemView.context.getDrawable(R.drawable.radius_borderline_12)
//                    it.typeface.isBold
//                }
                // 현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색처리
                if (position < firstDateIndex || position > lastDateIndex) {
                    it.setTextColor(Color.GRAY)
                    it.background = null
                }
            }
        }
    }

    fun replaceAll(date: Date) {
        this.date = date
        furangCalendar = FurangCalendar(this.date)

        furangCalendar.initBaseCalendar()
        this.dataList = furangCalendar.dateList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

}
