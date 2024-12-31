package com.learn.mynewsapp.domain.usecases.news

import com.learn.mynewsapp.data.local.db.ArticleDao
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        return newsRepository.upsertArticle(article)
    }

}