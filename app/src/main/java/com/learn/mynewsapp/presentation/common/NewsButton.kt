package com.learn.mynewsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.learn.mynewsapp.presentation.onBoarding.OnBoardingEvent
import com.learn.mynewsapp.presentation.onBoarding.OnBoardingViewModel
import com.learn.mynewsapp.ui.theme.MyNewsAppTheme
import com.learn.mynewsapp.ui.theme.NewsTypography

@Composable
fun NewsPrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = text,
            style = NewsTypography.newsPrimaryButton
        )
    }
}

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = NewsTypography.newsTextButton
        )
    }
}


@NewsComponentPreview
@Composable
fun NewsPrimaryButtonPreview() {
    NewsPreviewWrapper {
            NewsPrimaryButton("Confirm") { }
    }
}

@NewsComponentPreview
@Composable
fun NewsTextButtonPreview() {
    NewsPreviewWrapper {
        NewsTextButton("Back") { }
    }
}