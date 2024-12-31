package com.learn.mynewsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.presentation.NewsPaddingValues.MEDIUM_PADDING_2
import com.learn.mynewsapp.presentation.common.ArticleList
import com.learn.mynewsapp.presentation.common.SearchBar

@Composable
fun SearchScreen(
    event: (SearchScreenEvent) -> Unit,
    searchState: SearchState,
    navigateToDetail: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MEDIUM_PADDING_2)
            .statusBarsPadding()
    ) {
        SearchBar(
            modifier = Modifier,
            text = searchState.searchQuery,
            onClick = {},
            onValueChange = { newSearchQuery ->
                event(SearchScreenEvent.UpdateSearchQuery(newSearchQuery))
            },
            readOnly = false,
            onSearch = { event(SearchScreenEvent.SearchQuery) }
        )
        Spacer(Modifier.height(MEDIUM_PADDING_2))
        searchState.articles?.let {
            val articles = it.collectAsLazyPagingItems()

            ArticleList(
                modifier = Modifier,
                articles = articles,
                onClick = { article ->
                    navigateToDetail(article)
                }
            )
        }
    }
}