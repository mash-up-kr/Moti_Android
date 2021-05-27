package com.ahobsu.moti.presentation.ui.mypage

import androidx.lifecycle.LiveData
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.presentation.BaseViewModel


class MyPageViewModel(
    private val answerRepository: AnswerRepository
) : BaseViewModel() {

    val profileUrl = "https://avatars.githubusercontent.com/u/18034145?v=4"
}