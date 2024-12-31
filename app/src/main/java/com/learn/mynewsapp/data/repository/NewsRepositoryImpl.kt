package com.learn.mynewsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.learn.mynewsapp.data.local.db.ArticleDao
import com.learn.mynewsapp.data.remote.GetNewsPagingSource
import com.learn.mynewsapp.data.remote.NewsApi
import com.learn.mynewsapp.data.remote.SearchNewsPagingSource
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: ArticleDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                GetNewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun getLocalArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun getLocalArticle(url: String): Article? {
        return newsDao.getArticle(url = url)
    }
}