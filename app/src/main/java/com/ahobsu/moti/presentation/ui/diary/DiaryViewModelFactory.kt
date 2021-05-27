package com.ahobsu.moti.presentation.ui.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.domain.repository.AnswerRepository

class DiaryViewModelFactory(
    private val answerRepository: AnswerRepository
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiaryViewModel(answerRepository) as T
    }
}