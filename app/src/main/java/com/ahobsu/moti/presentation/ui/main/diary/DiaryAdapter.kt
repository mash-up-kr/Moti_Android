package com.ahobsu.moti.presentation.ui.main.diary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ItemDiaryBinding

class DiaryAdapter :
    ListAdapter<DiaryItemModel, DiaryAdapter.DiaryViewHolder>(DiaryImageComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        return DiaryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_diary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: ItemDiaryBinding? = androidx.databinding.DataBindingUtil.bind(itemView)

        fun bind(item: DiaryItemModel) {
            binding?.model = item
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: Int)
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
