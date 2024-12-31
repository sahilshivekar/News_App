package com.learn.mynewsapp.domain.usecases.news

import androidx.room.Delete

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val getLocalArticles: GetLocalArticles,
    val getLocalArticle: GetLocalArticle,
    val deleteArticle: DeleteArticle,
    val upsertArticle: UpsertArticle
)
