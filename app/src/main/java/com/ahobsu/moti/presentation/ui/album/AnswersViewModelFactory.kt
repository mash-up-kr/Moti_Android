package com.ahobsu.moti.presentation.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.domain.repository.AnswerRepository
import com.ahobsu.moti.domain.repository.MissionRepository

class AnswersViewModelFactory(
    private val answerRepository: AnswerRepository
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnswersViewModel(answerRepository) as T
    }
}