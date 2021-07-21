package com.ahobsu.moti.presentation.ui.diary.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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
    lateinit var date: Calendar

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

        @RequiresApi(Build.VERSION_CODES.M)
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(item: String) {
            binding?.model = item


            val firstDateIndex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead - 1

            // 오늘 날짜 처리 - 오늘날짜 date long 받아서 이번달인지 보기
            // val dateString: String = SimpleDateFormat("d", Locale.KOREA).format(date.time)

            binding?.tvCalenderDays?.let {
//                if (item == dateString) {
//                    it.background= itemView.context.getDrawable(R.drawable.radius_borderline_12)
//                    it.typeface.isBold
//                }

                // 현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색처리
                if (position < firstDateIndex || position > lastDateIndex) {
                    it.setTextColor(Color.GRAY)
                    it.background = null
                } else {
                    it.setTextColor(it.context.getColor(R.color.rose_gold))
                }

                it.setOnClickListener { view ->
                    if (position < firstDateIndex) {
                        date.run {
                            add(Calendar.MONTH, -1)
                        }
                    } else if (position > lastDateIndex) {
                        date.run {
                            add(Calendar.MONTH, +1)
                        }
                    }

                    val dateMonth = SimpleDateFormat("yyyy.MM", Locale.KOREA).format(date.time)
                    val dateDay = dateMonth + "." + it.text
                    mListener?.onItemClick(dateDay)
                }
            }
        }
    }

    fun replaceAll(date: Calendar) {
        this.date = date
        furangCalendar = FurangCalendar(this.date.time)
        furangCalendar.initBaseCalendar()
        this.dataList = furangCalendar.dateList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(date: String)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

}
