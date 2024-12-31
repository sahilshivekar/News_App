package com.learn.mynewsapp.domain.repository

import androidx.paging.PagingData
import com.learn.mynewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getLocalArticles(): Flow<List<Article>>

    suspend fun getLocalArticle(url: String): Article?
}