package com.ahobsu.moti.presentation.ui.diary.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
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

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var dataList: ArrayList<Int> = arrayListOf()
    private var writeDayList: List<String> = listOf()
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

            binding?.tvCalenderDays?.let {
                var dateYear = SimpleDateFormat("yyyy", Locale.KOREA).format(date.time).toInt()
                var dateMonth = SimpleDateFormat("MM", Locale.KOREA).format(date.time).toInt()

                if (position < firstDateIndex) {
                    if (dateMonth == 1) {
                        dateYear -= 1
                        dateMonth = 12
                    } else {
                        dateMonth -= 1
                    }
                } else if (position > lastDateIndex) {
                    if (dateMonth == 12) {
                        dateYear += 1
                        dateMonth = 1
                    } else {
                        dateMonth += 1
                    }
                }
                val dateString = String.format("%04d-%02d-%02d", dateYear, dateMonth, item.toInt())
                if (dateString in writeDayList) {
                    it.setTextColor(it.context.getColor(R.color.rose_gold))
                    it.setOnClickListener { _ ->
                        val dateString = String.format("%04d.%02d.%02d", dateYear, dateMonth, item.toInt())
                        mListener?.onItemClick(dateString)
                    }
                } else {
                    it.setTextColor(Color.GRAY)
                }
            }
        }
    }

    fun setWriteDayList(days: List<String>) {
        writeDayList = days
        notifyDataSetChanged()
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
