package com.ahobsu.moti.presentation.ui.question.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ItemQuestionBinding
import com.ahobsu.moti.presentation.BaseViewHolder
import com.ahobsu.moti.presentation.ui.main.model.MissionItemModel

class MissionAdapter :
    RecyclerView.Adapter<BaseViewHolder<ViewDataBinding, MissionItemModel>>() {

    private val items = mutableListOf<MissionItemModel>()

    private var onClickItemListener: OnClickItemListener? = null

    fun setOnClickItemListener(onClickItemListener: OnClickItemListener) {
        this.onClickItemListener = onClickItemListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding, MissionItemModel> {
        return MissionViewHolder(
            parent,
            onClickItemListener
        )
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewDataBinding, MissionItemModel>,
        position: Int
    ) {
        holder.bind(items[position])
    }

    fun replaceAll(items: List<MissionItemModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class MissionViewHolder(
        parent: ViewGroup,
        private val onClickItemListener: OnClickItemListener?
    ) :
        BaseViewHolder<ItemQuestionBinding, MissionItemModel>(parent, R.layout.item_question) {
        override fun bind(data: MissionItemModel) {
            binding.model = data
            binding.btnComplete.setOnClickListener {
                onClickItemListener?.onClickMission(data)
            }
        }
    }

    interface OnClickItemListener {
        fun onClickMission(data: MissionItemModel)
    }
}