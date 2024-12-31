package com.learn.mynewsapp.data.remote.dto

import com.learn.mynewsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)