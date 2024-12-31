package com.learn.mynewsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.presentation.NewsPaddingValues.MEDIUM_PADDING_2
import com.learn.mynewsapp.presentation.NewsPaddingValues.SMALL_PADDING_2

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>, // class responsible for accessing the paging data from the flow of paging data
    onClick: (Article) -> Unit
) {

    val isArticlesReady = handlePagingResult(articles)

    if (isArticlesReady) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING_2)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }
        }
    }
}

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()) {
        EmptyScreen()
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING_2)
        ) {
            items(count = articles.size) { index ->
                ArticleCard(article = articles[index], onClick = { onClick(articles[index]) })
            }
        }
    }
}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState

    val error: LoadState.Error? = listOf(
        loadState.refresh, loadState.prepend, loadState.append
    ).firstOrNull {
        it is LoadState.Error
    } as? LoadState.Error

    return when {
        loadState.refresh is LoadState.Loading -> {
            ArticleListShimmerEffect()
            false
        }

        error is LoadState.Error -> {
            EmptyScreen(error)
            false
        }

        articles.itemCount == 0 -> {
            EmptyScreen(customMsg = "Currently no articles available with this query!")
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ArticleListShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING_2)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect()
        }
    }
}

@NewsComponentPreview
@Composable
fun ArticleListPreview() {
    NewsPreviewWrapper {
        ArticleListShimmerEffect()
    }
}