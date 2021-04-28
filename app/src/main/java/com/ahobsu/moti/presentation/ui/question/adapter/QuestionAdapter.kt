package com.ahobsu.moti.presentation.ui.question.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ItemQuestionBinding
import com.ahobsu.moti.presentation.BaseViewHolder
import com.ahobsu.moti.presentation.ui.main.model.QuestionItemModel

class QuestionAdapter :
    RecyclerView.Adapter<BaseViewHolder<ViewDataBinding, QuestionItemModel>>() {

    private val items = mutableListOf<QuestionItemModel>()

    private var onClickItemListener: OnClickItemListener? = null

    fun setOnClickItemListener(onClickItemListener: OnClickItemListener) {
        this.onClickItemListener = onClickItemListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding, QuestionItemModel> {
        return QuestionViewHolder(
            parent,
            onClickItemListener
        )
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewDataBinding, QuestionItemModel>,
        position: Int
    ) {
        holder.bind(items[position])
    }

    fun replaceAll(items: List<QuestionItemModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class QuestionViewHolder(
        parent: ViewGroup,
        private val onClickItemListener: OnClickItemListener?
    ) :
        BaseViewHolder<ItemQuestionBinding, QuestionItemModel>(parent, R.layout.item_question) {
        override fun bind(data: QuestionItemModel) {
            binding.model = data
            binding.btnComplete.setOnClickListener {
                onClickItemListener?.onClickQuestion(it.id)
            }
        }
    }

    interface OnClickItemListener {
        fun onClickQuestion(id: Int)
    }
}