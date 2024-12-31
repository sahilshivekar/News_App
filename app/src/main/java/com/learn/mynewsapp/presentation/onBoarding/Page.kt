package com.learn.mynewsapp.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.learn.mynewsapp.R


data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "News at Your Fingertips",
        description = "Get the latest news from around the world, all in one app.",
        image = R.drawable.on1
    ),
    Page(
        title = "Quick News Summaries",
        description = "Get straight to the point with short, informative news pieces.",
        image = R.drawable.on2
    ),
    Page(
        title = "Your Personal News Archive",
        description = "Save articles to your personal collection and access them anytime, anywhere.",
        image = R.drawable.on3
    )
)
