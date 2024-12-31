package com.learn.mynewsapp.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.learn.mynewsapp.R
import com.learn.mynewsapp.presentation.IconSize

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    readOnly: Boolean,
    onSearch: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isClicked = interactionSource.collectIsPressedAsState().value

    LaunchedEffect(key1 = isClicked) {
        if (isClicked) onClick?.invoke()
    }

    Box(
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .searchBarBorder(),
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            readOnly = readOnly,
            interactionSource = interactionSource,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(IconSize.LARGE)
                )
            },
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = colorResource(id = R.color.input_background),
                focusedContainerColor = colorResource(id = R.color.input_background)
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            )
        )

    }
}


fun Modifier.searchBarBorder() = composed {
    if (!isSystemInDarkTheme()) {
        border(
            width = 1.dp,
            color = Color.Gray,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        this
    }
}

@NewsComponentPreview
@Composable
fun SearchBarPreview() {
    NewsPreviewWrapper {
        SearchBar(
            text = "",
            onValueChange = {},
            readOnly = false,
        ) {

        }
    }
}