package com.ahobsu.moti.data.dto

data class BaseData<D>(
   val status: Int,
   val message: String?,
   val data: D?
)