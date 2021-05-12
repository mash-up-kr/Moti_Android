package com.ahobsu.moti.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment() {

    lateinit var binding: B
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context;
    }

    fun checkCameraPermission() {
        val permissionCheck =
            ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            // 권한 없음
            Log.e("CameraPermission", "권한 없음")
            requestPermission()
        } else {
            // 권한이 이미 있음.
            Log.e("CameraPermission", "권한 이미 있음")
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            mContext as FragmentActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1000
        )
    }

}