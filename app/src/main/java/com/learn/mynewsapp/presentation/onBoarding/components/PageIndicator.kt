package com.learn.mynewsapp.presentation.onBoarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.learn.mynewsapp.presentation.NewsComponentsSize.PAGE_INDICATOR_SIZE
import com.learn.mynewsapp.presentation.common.NewsComponentPreview
import com.learn.mynewsapp.presentation.common.NewsPreviewWrapper
import com.learn.mynewsapp.ui.theme.BlueGray

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = BlueGray
) {
    Row(
        modifier = modifier
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(PAGE_INDICATOR_SIZE)
                    .clip(CircleShape)
                    .background(
                        if (selectedPage == index) selectedColor
                        else unselectedColor
                    )
            )
            Spacer(Modifier.width(if (index == pageCount - 1) 0.dp else 8.dp))
        }
    }
}

@NewsComponentPreview
@Composable
fun PageIndicatorPreview() {
    NewsPreviewWrapper {
        PageIndicator(
            pageCount = 3,
            selectedPage = 0,
        )
    }
}