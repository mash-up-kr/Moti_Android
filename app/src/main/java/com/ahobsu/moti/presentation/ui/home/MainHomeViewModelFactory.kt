package com.ahobsu.moti.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.domain.repository.AnswerRepository

class MainHomeViewModelFactory(
    private val answerRepository: AnswerRepository
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainHomeViewModel(answerRepository) as T
    }
}