package com.learn.mynewsapp.domain.usecases.news

import com.learn.mynewsapp.data.local.db.ArticleDao
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.domain.repository.NewsRepository

class GetLocalArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.getLocalArticle(url)
    }

}