package com.ahobsu.moti.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.domain.repository.AnswerRepository

class MyPageViewModelFactory(
    private val answerRepository: AnswerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyPageViewModel(answerRepository) as T
    }
}