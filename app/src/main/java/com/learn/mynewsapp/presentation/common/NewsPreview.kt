package com.learn.mynewsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.learn.mynewsapp.ui.theme.MyNewsAppTheme

/**
 * This preview annotation class is used to show component previews in both light and dark mode.
 */
@Preview(
    showBackground = true
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class NewsComponentPreview()

/**
 * This preview annotation class is used to show screen previews in both light and dark mode.
 */
@Preview(
    showSystemUi = true,
    device = Devices.PIXEL_7_PRO
)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_7_PRO
)
annotation class NewsScreenPreview()

/**
 * This function is used to apply the [MyNewsAppTheme] and a [Surface] behind the composable.
 */
@Composable
fun NewsPreviewWrapper(content: @Composable () -> Unit) {
    MyNewsAppTheme {
        Surface {
            content()
        }
    }
}