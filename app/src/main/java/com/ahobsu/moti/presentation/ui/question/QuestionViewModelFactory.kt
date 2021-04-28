package com.ahobsu.moti.presentation.ui.question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.domain.repository.MissionRepository

class QuestionViewModelFactory(
    private val missionRepository: MissionRepository
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuestionViewModel(missionRepository) as T
    }

}