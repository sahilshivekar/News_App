package com.learn.mynewsapp.presentation.details

import com.learn.mynewsapp.domain.model.Article

sealed class DetailsScreenEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsScreenEvent()

}
