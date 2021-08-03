package com.ahobsu.moti.presentation.ui.diary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ItemDiaryBinding
import com.ahobsu.moti.presentation.ui.diary.model.DiaryItemModel

class DiaryAdapter :
    ListAdapter<DiaryItemModel, DiaryAdapter.DiaryViewHolder>(
        DiaryImageComparator
    ) {
     private var lastDayList: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        return DiaryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_diary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DiaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ItemDiaryBinding? = androidx.databinding.DataBindingUtil.bind(itemView)
        fun bind(item: DiaryItemModel) {
            binding?.model = item
            if (item.date in lastDayList){
                item.isLastMonthItem = true
            } else false
            binding?.itemDiary?.setOnClickListener {
                mListener?.onItemClick(item)
            }

        }
    }

    fun setWriteDayList(days: List<String>) {
        val item = emptyList<String>().toMutableList()
        val sortedList = days.sorted()
        var dateString = sortedList[0]
        for (i in 1 until sortedList.size) {
            val dateString2 = sortedList[i]
            if (dateString.substring(0, 7) != dateString2.substring(0, 7)) {
                item += dateString
            }
            dateString = dateString2
        }
        item += sortedList[sortedList.size - 1]
        lastDayList = item
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(id: DiaryItemModel)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }
}

object DiaryImageComparator : DiffUtil.ItemCallback<DiaryItemModel>() {
    override fun areItemsTheSame(
        oldItem: DiaryItemModel,
        newItem: DiaryItemModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DiaryItemModel,
        newItem: DiaryItemModel
    ): Boolean {
        return oldItem == newItem
    }

}
