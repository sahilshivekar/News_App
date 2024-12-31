package com.learn.mynewsapp.domain.usecases.news

import androidx.paging.PagingData
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery, sources)
    }
}