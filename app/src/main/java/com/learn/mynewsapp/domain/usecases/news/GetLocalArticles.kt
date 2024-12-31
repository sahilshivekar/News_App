package com.learn.mynewsapp.domain.usecases.news

import com.learn.mynewsapp.data.local.db.ArticleDao
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetLocalArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getLocalArticles()
    }

}