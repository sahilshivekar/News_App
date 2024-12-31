package com.learn.mynewsapp.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.domain.model.Source
import com.learn.mynewsapp.presentation.bookmark.BookmarkScreen
import com.learn.mynewsapp.presentation.bookmark.BookmarkScreenViewModel
import com.learn.mynewsapp.presentation.details.DetailsScreen
import com.learn.mynewsapp.presentation.details.DetailsScreenEvent
import com.learn.mynewsapp.presentation.details.DetailsScreenViewModel
import com.learn.mynewsapp.presentation.home.HomeScreen
import com.learn.mynewsapp.presentation.home.HomeViewModel
import com.learn.mynewsapp.presentation.navigation.news_navigator.NewsNavigator
import com.learn.mynewsapp.presentation.onBoarding.OnBoardingScreen
import com.learn.mynewsapp.presentation.onBoarding.OnBoardingViewModel
import com.learn.mynewsapp.presentation.search.SearchScreen
import com.learn.mynewsapp.presentation.search.SearchScreenViewModel
import com.learn.mynewsapp.presentation.search.SearchState

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route){
                NewsNavigator()
            }
        }
    }
}