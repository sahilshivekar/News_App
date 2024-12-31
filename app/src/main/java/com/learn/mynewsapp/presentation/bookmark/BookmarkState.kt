package com.learn.mynewsapp.presentation.bookmark

import com.learn.mynewsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)