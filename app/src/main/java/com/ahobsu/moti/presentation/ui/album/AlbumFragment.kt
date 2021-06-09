package com.ahobsu.moti.presentation.ui.album

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahobsu.moti.R
import com.ahobsu.moti.data.injection.Injection
import com.ahobsu.moti.databinding.FragmentAlbumBinding
import com.ahobsu.moti.presentation.BaseFragment
import com.ahobsu.moti.presentation.ui.album.adapter.AlbumAdapter


class AlbumFragment :
    BaseFragment<FragmentAlbumBinding>(R.layout.fragment_album) {

    private val viewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            AlbumViewModelFactory(Injection.provideAnswerRepository())
        ).get(AlbumViewModel::class.java)
    }

    private val albumAdapter by lazy {
        AlbumAdapter().apply {
            setOnItemClickListener(object : AlbumAdapter.OnItemClickListener {
                override fun onItemClick(id: Int) {
                    TODO("Not yet implemented")
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

        binding.tvAlvum.setOnClickListener {
            binding.scrollViewAlbumTop.scrollTo((Math.random() * 1800).toInt(), 0)
        }

        viewModel.albumList.observe(viewLifecycleOwner) { it ->
            albumAdapter.submitList(it)
        }
    }


    private fun initRecyclerView() {
        with(binding.albumRecyclerView) {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    companion object {
        fun newInstance() =
            AlbumFragment()
    }

}