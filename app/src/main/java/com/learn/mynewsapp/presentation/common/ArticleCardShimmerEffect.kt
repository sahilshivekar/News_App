package com.learn.mynewsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.learn.mynewsapp.presentation.NewsPaddingValues.EXTRA_SMALL_PADDING_2
import com.learn.mynewsapp.presentation.NewsPaddingValues.SMALL_PADDING_1

@Composable
fun ArticleCardShimmerEffect(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .padding(SMALL_PADDING_1)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )

        Column(
            modifier = Modifier
                .padding(SMALL_PADDING_1)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth(0.4f)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
            }
        }
    }
}

@NewsComponentPreview
@Composable
fun ArticleCardShimmerEffectPreview() {
    NewsPreviewWrapper {
        ArticleCardShimmerEffect()
    }
}