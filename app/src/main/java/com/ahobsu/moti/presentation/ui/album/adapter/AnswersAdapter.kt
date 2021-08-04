package com.ahobsu.moti.presentation.ui.album.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ItemAnswerBinding
import com.ahobsu.moti.presentation.BaseViewHolder
import com.ahobsu.moti.presentation.ui.album.model.AnswerItemModel

class AnswersAdapter :
    RecyclerView.Adapter<BaseViewHolder<ViewDataBinding, AnswerItemModel>>() {

    private val items = mutableListOf<AnswerItemModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding, AnswerItemModel> {
        return MissionViewHolder(parent)
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewDataBinding, AnswerItemModel>,
        position: Int
    ) {
        holder.bind(items[position])
    }

    fun replaceAll(items: List<AnswerItemModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class MissionViewHolder(
        parent: ViewGroup
    ) :
        BaseViewHolder<ItemAnswerBinding, AnswerItemModel>(parent, R.layout.item_answer) {
        override fun bind(data: AnswerItemModel) {
            binding.viewModel = data
        }
    }

}