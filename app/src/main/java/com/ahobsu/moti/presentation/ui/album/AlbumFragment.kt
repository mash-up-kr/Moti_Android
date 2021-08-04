package com.ahobsu.moti.presentation.ui.album

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentAlbumBinding
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.presentation.ui.album.adapter.AlbumAdapter


class AlbumFragment :
    BaseFragment<FragmentAlbumBinding>(R.layout.fragment_album) {

    private var listSize = 0

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            AlbumViewModelFactory(Injection.provideAnswerRepository())
        ).get(AlbumViewModel::class.java)
    }

    private val albumAdapter by lazy {
        AlbumAdapter().apply {
            setOnItemClickListener(object : AlbumAdapter.OnItemClickListener {
                override fun onItemClick(id: Int, size: Int) {
                    val intent = Intent(activity, AnswersActivity::class.java)
                    intent.putExtra("answerListId", id)
                    intent.putExtra("answerListSize", size)
                    startActivity(intent)
                }
            })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        initRecyclerView()

        binding.scrollViewAlbumTop.setOnTouchListener { v, event ->
            true
        }

//        binding.tvAlvum.setOnClickListener {
//            //TODO("화면 구성 후 스크롤 위치 변경하도록 수정")
//            binding.scrollViewAlbumTop.scrollTo((Math.random() * 1800).toInt(), 0)
//        }

        viewModel.albumList.observe(viewLifecycleOwner) { it ->
            albumAdapter.submitList(it)
            listSize = it.size
        }
    }


    inner class SpaceDecoration(private val size: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left += size
            } else if (parent.getChildAdapterPosition(view) == listSize-1){
                outRect.right += size
            }
        }
    }

    private fun initRecyclerView() {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(
            displayMetrics
        )
        val deviceWidth: Int = displayMetrics.widthPixels
        val deco = SpaceDecoration(deviceWidth / 4)

        binding.albumRecyclerView.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(deco)
        }
    }

    companion object {
        fun newInstance() =
            AlbumFragment()
    }

}