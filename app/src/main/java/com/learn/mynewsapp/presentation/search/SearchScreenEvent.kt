package com.learn.mynewsapp.presentation.search

sealed class SearchScreenEvent {
    data class UpdateSearchQuery(val newSearchQuery: String) : SearchScreenEvent()
    data object SearchQuery : SearchScreenEvent()
}