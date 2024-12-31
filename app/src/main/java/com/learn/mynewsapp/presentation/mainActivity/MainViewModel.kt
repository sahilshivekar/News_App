package com.learn.mynewsapp.presentation.mainActivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.mynewsapp.domain.usecases.app_entry.AppEntryUseCases
import com.learn.mynewsapp.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var isSplashScreenVisible by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Route.NewsNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { isReturningUser ->
            startDestination =
                if (isReturningUser) Route.NewsNavigation.route else Route.AppStartNavigation.route
            delay(200)
            isSplashScreenVisible = false
        }.launchIn(viewModelScope)
    }
}