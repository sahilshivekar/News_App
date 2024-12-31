package com.learn.mynewsapp.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.learn.mynewsapp.R
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    customMsg: String? = null
) {
    var message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }

    @DrawableRes
    var icon by remember {
        mutableIntStateOf(R.drawable.ic_network_error)
    }

    if (error == null) {
        message = customMsg ?: "You have not saved any news so far!"
        icon = R.drawable.ic_search_document
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0.0f,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(alphaAnimation = alphaAnimation, message = message, icon = icon)
}

@Composable
fun EmptyContent(alphaAnimation: Float, message: String, @DrawableRes icon: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnimation)
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier
                .padding(10.dp)
                .alpha(alphaAnimation)
        )
    }
}

fun parseErrorMessage(error: LoadState.Error?): String {
    return when (error?.error) {
        is SocketTimeoutException -> "Server Unavailable"
        is ConnectException -> "Internet Unavailable"
        else -> "Unknown Error"
    }
}

@NewsComponentPreview
@Composable
fun EmptyScreenPreview() {
    NewsPreviewWrapper {
        EmptyContent(alphaAnimation = 0.3f, message = "Internet Unavailable.",R.drawable.ic_network_error)
    }
}
