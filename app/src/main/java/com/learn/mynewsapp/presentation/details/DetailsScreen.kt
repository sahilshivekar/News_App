package com.learn.mynewsapp.presentation.details

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.presentation.NewsPaddingValues.MEDIUM_PADDING_2
import com.learn.mynewsapp.presentation.details.components.DetailsTopBar

@Composable
fun DetailsScreen(
    article: Article,
    onEvent: (DetailsScreenEvent) -> Unit,
    navigateUp: () -> Unit,
    viewModel: DetailsScreenViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.setupBookmarkState(article.url)
    }

    LaunchedEffect(key1 = viewModel.toastMsgState) {
        if (viewModel.toastMsgState != null) {
            Toast.makeText(context, viewModel.toastMsgState, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailsTopBar(
            onShareClick = {
                val intent = Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            },
            onBrowseClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            },
            onSaveClick = { onEvent(DetailsScreenEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp,
            isBookmarkedState = viewModel.isBookmarkedState
        )
        Column(
            modifier = Modifier
                .padding(MEDIUM_PADDING_2)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(MEDIUM_PADDING_2))
            Text(
                text = "Source: ${article.source.name}",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(MEDIUM_PADDING_2))
            Text(
                text = article.content,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

//@NewsScreenPreview
//@Composable
//fun ArticleCardPreview() {
//    NewsPreviewWrapper {
//        DetailsScreen(
//            article = Article(
//                author = "",
//                title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - ",
//                description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - ",
//                content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to [+1131 chars]",
//                publishedAt = "2023-06-16T22:24:33Z",
//                source = Source(
//                    id = "", name = "bbc"
//                ),
//                url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
//                urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
//            ),
//            onEvent = {},
//            navigateUp = {}
//        )
//    }
//}