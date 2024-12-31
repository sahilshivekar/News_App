package com.learn.mynewsapp.presentation.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var toastMsgState by mutableStateOf<String?>(null)
        private set


    var isBookmarkedState by mutableStateOf(false)
        private set

    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val localArticle = newsUseCases.getLocalArticle(event.article.url)
                    if (localArticle == null) {
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }
                }
            }
        }
    }

    suspend fun setupBookmarkState(url: String) {
        newsUseCases.getLocalArticle(url)?.let {
            isBookmarkedState = true
        }
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article)
        isBookmarkedState = true
        toastMsgState = "Article Saved"
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article)
        isBookmarkedState = false
        toastMsgState = "Article Deleted"
    }
}

