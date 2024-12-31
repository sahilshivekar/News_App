package com.learn.mynewsapp.presentation.onBoarding

sealed class OnBoardingEvent {
    data object SaveAppEntry : OnBoardingEvent()
}


