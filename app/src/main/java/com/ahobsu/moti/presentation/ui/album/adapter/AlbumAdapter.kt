package com.ahobsu.moti.presentation.ui.album.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.databinding.ItemAlbumBinding
import com.ahobsu.moti.presentation.ui.album.model.AlbumItemModel

class AlbumAdapter :
    ListAdapter<AlbumItemModel, AlbumAdapter.AlbumViewHolder>(AlbumImageComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ItemAlbumBinding? = androidx.databinding.DataBindingUtil.bind(itemView)

        fun bind(item: AlbumItemModel) {
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

object AlbumImageComparator : DiffUtil.ItemCallback<AlbumItemModel>() {
    override fun areItemsTheSame(
        oldItem: AlbumItemModel,
        newItem: AlbumItemModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AlbumItemModel,
        newItem: AlbumItemModel
    ): Boolean {
        return oldItem == newItem
    }
}