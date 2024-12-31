package com.learn.mynewsapp.presentation.onBoarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.learn.mynewsapp.presentation.NewsPaddingValues.MEDIUM_PADDING_2
import com.learn.mynewsapp.presentation.common.NewsPreviewWrapper
import com.learn.mynewsapp.presentation.common.NewsPrimaryButton
import com.learn.mynewsapp.presentation.common.NewsScreenPreview
import com.learn.mynewsapp.presentation.common.NewsTextButton
import com.learn.mynewsapp.presentation.onBoarding.components.OnBoardingPage
import com.learn.mynewsapp.presentation.onBoarding.components.PageIndicator
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState {
            pages.size
        }

        val buttonsState by remember {
            derivedStateOf {
                val isFirstPage = pagerState.currentPage == 0
                val isLastPage = pagerState.pageCount - 1 == pagerState.currentPage

                when {
                    isFirstPage -> {
                        listOf("", "Next")
                    }

                    isLastPage -> {
                        listOf("Back", "Confirm")
                    }

                    else -> {
                        listOf("Back", "Next")
                    }
                }
            }
        }
        HorizontalPager(
            state = pagerState
        ) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING_2)
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                pageCount = pages.size,
                selectedPage = pagerState.currentPage
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val scope = rememberCoroutineScope()
                if (pagerState.currentPage != 0) {
                    NewsTextButton(
                        text = buttonsState[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    )
                }
                NewsPrimaryButton(
                    text = buttonsState[1],
                    onClick = {
                        if (pagerState.currentPage == pagerState.pageCount - 1) {
                            event(OnBoardingEvent.SaveAppEntry)
                        } else {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
    }
}


@NewsScreenPreview
@Composable
fun OnBoardingScreenPreview() {
    NewsPreviewWrapper {
        val viewModel: OnBoardingViewModel = hiltViewModel()
        OnBoardingScreen(viewModel::onEvent)
    }
}