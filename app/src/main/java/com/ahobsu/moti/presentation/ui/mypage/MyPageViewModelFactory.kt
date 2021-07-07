package com.ahobsu.moti.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.domain.repository.UserRepository

class MyPageViewModelFactory(
    private val  userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyPageViewModel(userRepository) as T
    }
}