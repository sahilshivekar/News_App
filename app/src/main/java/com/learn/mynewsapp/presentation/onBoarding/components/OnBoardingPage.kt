package com.learn.mynewsapp.presentation.onBoarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.learn.mynewsapp.presentation.NewsPaddingValues.MEDIUM_PADDING_2
import com.learn.mynewsapp.presentation.common.NewsPreviewWrapper
import com.learn.mynewsapp.presentation.common.NewsScreenPreview
import com.learn.mynewsapp.presentation.onBoarding.Page
import com.learn.mynewsapp.presentation.onBoarding.pages
import com.learn.mynewsapp.ui.theme.NewsTypography.bodyMedium
import com.learn.mynewsapp.ui.theme.NewsTypography.headlineBold

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING_2))
        Text(
            modifier = Modifier.padding(horizontal = MEDIUM_PADDING_2),
            text = page.title,
            style = headlineBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier.padding(horizontal = MEDIUM_PADDING_2),
            text = page.description,
            style = bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


@NewsScreenPreview
@Composable
fun OnBoardingPagePreview() {
    NewsPreviewWrapper {
        OnBoardingPage(Modifier, pages[0])
    }
}