package com.ahobsu.moti.presentation.ui.main.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahobsu.moti.domain.repository.AnswerRepository

class AlbumViewModelFactory(
    private val answerRepository: AnswerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumViewModel(answerRepository) as T
    }
}