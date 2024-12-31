package com.learn.mynewsapp.presentation.bookmark

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkScreenViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var state by mutableStateOf(BookmarkState(emptyList()))
        private set

    init {
        getLocalArticles()
    }

    private fun getLocalArticles() {
        newsUseCases.getLocalArticles().onEach {
            state = state.copy(articles = it.asReversed())
        }.launchIn(viewModelScope)
    }
}