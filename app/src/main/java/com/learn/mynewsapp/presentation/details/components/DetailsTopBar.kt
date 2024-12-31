package com.learn.mynewsapp.presentation.details.components

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.learn.mynewsapp.R
import com.learn.mynewsapp.presentation.common.NewsComponentPreview
import com.learn.mynewsapp.presentation.common.NewsPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onSaveClick: () -> Unit,
    onBrowseClick: () -> Unit,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    isBookmarkedState: Boolean
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveClick) {
                Icon(
                    painter = when (isBookmarkedState) {
                        true -> painterResource(id = R.drawable.baseline_bookmark_added_24)
                        false -> painterResource(R.drawable.ic_bookmark)
                    },
                    contentDescription = null
                )
            }
            IconButton(onClick = onBrowseClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = null
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@NewsComponentPreview
@Composable
fun DetailsTopBarPreview() {
    NewsPreviewWrapper {
        DetailsTopBar({}, {}, {}, {}, true)
    }
}
