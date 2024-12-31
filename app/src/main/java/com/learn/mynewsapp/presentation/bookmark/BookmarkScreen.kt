package com.learn.mynewsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.learn.mynewsapp.R
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.presentation.NewsPaddingValues.MEDIUM_PADDING_2
import com.learn.mynewsapp.presentation.common.ArticleList

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(MEDIUM_PADDING_2)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(MEDIUM_PADDING_2))

        ArticleList(
            articles = state.articles,
            onClick = navigateToDetails
        )
    }

}
