package com.learn.mynewsapp.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.learn.mynewsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    var searchState by mutableStateOf(SearchState())
        private set

    fun onEvent(event: SearchScreenEvent) {
        return when (event) {
            is SearchScreenEvent.SearchQuery -> {
                searchQuery()
            }

            is SearchScreenEvent.UpdateSearchQuery -> {
                searchState = searchState.copy(searchQuery = event.newSearchQuery)
                searchQuery()
            }
        }
    }

    private fun searchQuery() {
        val articles = newsUseCases.searchNews(
            searchQuery = searchState.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        searchState = searchState.copy(articles = articles)
    }
}