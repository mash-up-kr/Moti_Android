package com.ahobsu.moti.presentation.ui.question

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ItemQuestionBinding
import com.ahobsu.moti.domain.entity.Mission
import com.ahobsu.moti.presentation.BaseViewHolder

class QuestionAdapter :
    RecyclerView.Adapter<BaseViewHolder<ViewDataBinding, QuestionItemViewModel>>() {

    private val items = mutableListOf<QuestionItemViewModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding, QuestionItemViewModel> {
        return QuestionViewHolder(parent)
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewDataBinding, QuestionItemViewModel>,
        position: Int
    ) {
        holder.bind(items[position])
    }

    fun replaceAll(items: List<QuestionItemViewModel>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class QuestionViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemQuestionBinding, QuestionItemViewModel>(parent, R.layout.item_question) {
        override fun bind(data: QuestionItemViewModel) {
            binding.model = data
        }
    }
}