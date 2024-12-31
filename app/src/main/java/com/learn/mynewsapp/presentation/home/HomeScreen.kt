package com.learn.mynewsapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.learn.mynewsapp.R
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.presentation.NewsPaddingValues.MEDIUM_PADDING_2
import com.learn.mynewsapp.presentation.common.ArticleList
import com.learn.mynewsapp.presentation.common.NewsPreviewWrapper
import com.learn.mynewsapp.presentation.common.NewsScreenPreview
import com.learn.mynewsapp.presentation.common.SearchBar
import com.learn.mynewsapp.presentation.navigation.Route

@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    val title by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles
                    .itemSnapshotList
                    .items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MEDIUM_PADDING_2)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier.height(30.dp)
        )

        Spacer(Modifier.height(MEDIUM_PADDING_2))

        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            onClick = {
                navigateToSearch()
            },
            onValueChange = {},
            readOnly = true,
            onSearch = {}
        )

        Spacer(Modifier.height(MEDIUM_PADDING_2))

        Text(
            text = title,
            modifier = Modifier.basicMarquee(),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(MEDIUM_PADDING_2))

        ArticleList(
            modifier = Modifier,
            articles = articles,
            onClick = { article ->
                navigateToDetails(article)
            }
        )
    }
}


@NewsScreenPreview
@Composable
fun HomeScreenPreview() {
    NewsPreviewWrapper {
//        HomeScreen()
    }
}